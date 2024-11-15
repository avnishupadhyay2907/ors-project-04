package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.PositionBean;

import in.co.rays.model.PositionModel;

public class TestPosition {

	public static void main(String[] args) throws Exception {

		//testAdd();
		// testUpdate();
		// testDelete();
		 //testSearch();
		//testFindByPk();
		 testFindByDesignation();

	}

	private static void testFindByDesignation() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();

		bean = model.findByDesignation("manager");
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getDesignation());
			System.out.println(bean.getOpeningDate());
			System.out.println(bean.getRequiredExperience());
			System.out.println(bean.getCondition());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
			System.out.println("designation already exist");
		} else {
			System.out.println("designation id not exist");
		}

	}

	private static void testFindByPk() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();

		bean = model.findByPk(1);
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getDesignation());
			System.out.println(bean.getOpeningDate());
			System.out.println(bean.getRequiredExperience());
			System.out.println(bean.getCondition());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
			System.out.println("designation already exist");
		} else {
			System.out.println("designation not exist");
		}

	}

	private static void testSearch() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();

		bean.setDesignation("manager");

		List list = model.search(bean, 0, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (PositionBean) it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getDesignation());
			System.out.println(bean.getOpeningDate());
			System.out.println(bean.getRequiredExperience());
			System.out.println(bean.getCondition());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		}

	}

	private static void testDelete() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();

		bean.setId(2);
		model.delete(bean);

	}

	private static void testUpdate() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDesignation("ceo");
		bean.setOpeningDate(sdf.parse("2024-08-08"));
		bean.setRequiredExperience("4 years");
		bean.setCondition("open");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy(null);
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(null);
		bean.setId(1);

		model.update(bean);
	}

	private static void testAdd() throws Exception {

		PositionBean bean = new PositionBean();
		PositionModel model = new PositionModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDesignation("manager");
		bean.setOpeningDate(sdf.parse("2007-01-29"));
		bean.setRequiredExperience("4 years");
		bean.setCondition("open");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy(null);
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(null);

		model.add(bean);

	}

}
