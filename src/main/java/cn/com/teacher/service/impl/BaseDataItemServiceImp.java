package cn.com.teacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.teacher.entity.BaseDataItem;
import cn.com.teacher.entity.Result;
import cn.com.teacher.mapper.BaseDataItemMapper;
import cn.com.teacher.service.BaseDataItemService;
import cn.com.teacher.util.ResultUtil;

@Service("dataItem")
public class BaseDataItemServiceImp implements BaseDataItemService {
	
	@Autowired
	private BaseDataItemMapper baseDataItemMapper;
	
	
	@Override
	public List<BaseDataItem> getDataItemByCategoryId(Integer categoryId) {
		 List<BaseDataItem> items = baseDataItemMapper.getDataItemsByCategoryId(categoryId );
		return items;
	}

	@Override
	public void insertItem(BaseDataItem item) {
	     baseDataItemMapper.insert(item);
	}

	@Override
	public void updateItem(BaseDataItem item) {
		baseDataItemMapper.update(item);

	}

	@Override
	public BaseDataItem updateSequence(Integer id, String type) {
		BaseDataItem item = baseDataItemMapper.updateSequence(id, type);
		return item;
	}
	
	@Override
	public BaseDataItem getDataItemsById(Integer id){
		BaseDataItem item = baseDataItemMapper.getDataItemsById(id);
		return item;
	}

	@Override
	public Result moveItem(List<BaseDataItem> list) {
		if (list.size() != 2) {
			return ResultUtil.buildFail("排序失败");
		}else{
			BaseDataItem currItem = list.get(0);
			BaseDataItem nextItem = list.get(1);
			//交换序列号
			int currSeq =  currItem.getSequence();
			int nextSeq =  nextItem.getSequence();
			currItem.setSequence(nextSeq);
			nextItem.setSequence(currSeq);
			baseDataItemMapper.update(currItem);
			baseDataItemMapper.update(nextItem);
			return ResultUtil.buildSuccess("排序成功");
		}
	}

	@Override
	public int getMaxItemSequence() {
		Integer sequence =  0;
		if(null!=baseDataItemMapper.getMaxItemSequence()){
			sequence = baseDataItemMapper.getMaxItemSequence();
		}
		return sequence;
		
	}

	@Override
	public Result deleteItem(Integer id) {
		baseDataItemMapper.delete(id);
		return ResultUtil.buildSuccess("删除成功");
	}
	
	@Override
	public boolean existsItemCode(Integer categoryId,String dataItemCode,Integer eidtId){
		
		List<BaseDataItem> itemList = baseDataItemMapper.getDataItem(categoryId, dataItemCode);
		if(itemList!=null&&itemList.size()>0){
			if(eidtId==null){
				return true;
			}else{
				if(itemList.get(0).getId().intValue()==eidtId.intValue()){
					return false;
				}else{
					return true;
				}
			}
			
		}
		return false;
	}
}
