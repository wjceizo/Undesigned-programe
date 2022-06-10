package self.control.educational.service.impl;

import onway.org.cn.comm.utils.date.DateUtils;
import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.ActivitysDao;
import self.control.educational.entity.Activitys;
import self.control.educational.service.ActivitysService;

import java.util.List;

@Service("activitysService")
public class ActivitysServiceImpl implements ActivitysService {
	
	@Autowired
    private ActivitysDao activitysDao;
	
	@Override
	@Transactional
	public void add(Activitys activitys) {
		
		activitysDao.insert(activitys);
	}

	@Override
	@Transactional
	public void update(Activitys activitys) {
		
		activitysDao.updateById(activitys);
	}

	@Override
	@Transactional
	public void delete(Activitys activitys) {
		
		activitysDao.deleteById(activitys);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Activitys getOne(String id) {
		
		Activitys activitys = activitysDao.selectById(id);
		
		return activitys;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Activitys> getShowList(){

		String nowTime = DateUtils.getNowDate("yyyy-MM-dd");

		QueryWrapper<Activitys> qw = new QueryWrapper<Activitys>();
		qw.ge("create_time", nowTime);

		qw.orderByDesc("create_time");

		List<Activitys> list = activitysDao.selectList(qw);

		return list;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Activitys activitys){
				
		QueryWrapper<Activitys> qw = new QueryWrapper<Activitys>();

		if(StringUtils.isNotNullOrEmpty(activitys.getTitle())){

			qw.like("title", activitys.getTitle());
		}

		if(StringUtils.isNotNullOrEmpty(activitys.getLoc())){

			qw.like("loc", activitys.getLoc());
		}

		qw.orderByDesc("create_time");

		Page<Activitys> page = 
				activitysDao.selectPage(new Page<Activitys>(pageIndex, pageSize), qw);
		
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