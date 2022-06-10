package self.control.educational.service.impl;

import onway.org.cn.comm.utils.date.DateUtils;
import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.NoticesDao;
import self.control.educational.entity.Notices;
import self.control.educational.service.NoticesService;

import java.util.List;

@Service("noticesService")
public class NoticesServiceImpl implements NoticesService {
	
	@Autowired
    private NoticesDao noticesDao;
	
	@Override
	@Transactional
	public void add(Notices notices) {
		
		noticesDao.insert(notices);
	}

	@Override
	@Transactional
	public void update(Notices notices) {
		
		noticesDao.updateById(notices);
	}

	@Override
	@Transactional
	public void delete(Notices notices) {
		
		noticesDao.deleteById(notices);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Notices getOne(String id) {
		
		Notices notices = noticesDao.selectById(id);
		
		return notices;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Notices> getShowList(){

		String nowTime = DateUtils.getNowDate("yyyy-MM-dd");

		QueryWrapper<Notices> qw = new QueryWrapper<Notices>();

		qw.le("start_time", nowTime);
		qw.ge("end_time", nowTime);

		qw.orderByDesc("start_time");

		List<Notices> list = noticesDao.selectList(qw);

		return list;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Notices notices){
				
		QueryWrapper<Notices> qw = new QueryWrapper<Notices>();

		if(StringUtils.isNotNullOrEmpty(notices.getTitle())){

			qw.like("title", notices.getTitle());
		}

		qw.orderByDesc("start_time");
		
		Page<Notices> page = 
				noticesDao.selectPage(new Page<Notices>(pageIndex, pageSize), qw);
		
		return parsePage(page);
	}
	
	/**
	 * 转化分页查询的结果
	 */	
	public onway.org.cn.comm.web.msg.Page 
								parsePage(Page p){
		
		onway.org.cn.comm.web.msg.Page page = 
								new onway.org.cn.comm.web.msg.Page();
		
		page.setPageIndex(p.getCurrent());
		page.setPageSize(p.getSize());
		
		Long pageTotal = (p.getTotal() % p.getSize()) == 0 ? 
							(p.getTotal() / p.getSize()) : (p.getTotal() / p.getSize() + 1);
		
		page.setPageTotal(pageTotal);
		page.setCount(p.getTotal());
		page.setData(p.getRecords());
		
		return page;
	}
}