package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.PatientBean;
import in.co.rays.model.PatientModel;

public class TestPatient {

	public static void main(String[] args) throws Exception {
		/*
		 * testAdd();
		 */
		
	//	testUpdate(); 
		//testDelete(); 
		
		//  testSearch(); 
		// testFindByPk(); 
		  testFindByMobile();
		 
		
	}

	private static void testFindByMobile() throws Exception {
		PatientBean bean = new PatientBean();

		PatientModel model = new PatientModel();
		bean = model.findByMobile("7645252894");
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDateOFVisit());
			System.out.println(bean.getMobile());
			System.out.println(bean.getDisease());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
			System.out.println("mobile no  already exist");
		} else {
			System.out.println("mobile no not exist");
		}
		
	}

	private static void testFindByPk() throws Exception {
		PatientBean bean = new PatientBean();

		PatientModel model = new PatientModel();
		bean = model.findByPk(1);
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDateOFVisit());
			System.out.println(bean.getMobile());
			System.out.println(bean.getDisease());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		} else {
			System.out.println("login id not exist");
		}
		
	}

	private static void testSearch() throws Exception {
		PatientBean bean = new PatientBean();

		PatientModel model = new PatientModel();

		bean.setName("vansha");

		List list = model.search(bean, 0, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (PatientBean) it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDateOFVisit());
			System.out.println(bean.getMobile());
			System.out.println(bean.getDisease());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		}		
	}

	private static void testDelete() throws Exception {
		PatientBean bean = new PatientBean();
		PatientModel model = new PatientModel();

		bean.setId(2);
		model.delete(bean);
		
	}

	private static void testUpdate() throws Exception {
		PatientBean bean = new PatientBean();
		PatientModel model = new PatientModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setName("ramesh");
		bean.setDateOFVisit(sdf.parse("2024-11-09"));
		bean.setMobile("7645252894");
		bean.setDisease("viral");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy(null);
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(null);
		bean.setId(2);
		model.update(bean);		
	}

	private static void testAdd() throws Exception {

		PatientBean bean = new PatientBean();
		PatientModel model = new PatientModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setName("vansha");
		bean.setDateOFVisit(sdf.parse("2024-11-09"));
		bean.setMobile("7645252894");
		bean.setDisease("viral");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy(null);
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(null);

		model.add(bean);
		
	}
	
}
