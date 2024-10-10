package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;

public class TestUser {

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		//testSearch();
		testfindByPk();
		//testfindByLoginId();
	}

	private static void testfindByLoginId() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByLoginId("uavnish2907@gmail.com");
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLoginId());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		System.out.println("login id already exist");
		} else {
System.out.println("login id not exist");
		}	
	}

	private static void testfindByPk() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByPk(1);
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLoginId());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		System.out.println("login id already exist");
		} else {
System.out.println("login id not exist");
		}		
	}

	private static void testSearch() throws Exception {
		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		bean.setFirstName("avnish");

		List list = model.search(bean);

		Iterator it = list.iterator();

		while (it.hasNext()) {


			bean = (UserBean) it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLoginId());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		}

	}

	private static void testDelete() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		bean.setId(2);
		model.delete(bean);
	}

	private static void testUpdate() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("avnish");
		bean.setLastName("upadhyay");
		bean.setLoginId("uavnish2907@gmail.com");
		bean.setPassword("avnish@2907");
		bean.setDob(sdf.parse("2007-01-29"));
		bean.setMobileNo("7648880017");
		bean.setRoleId(1);
		bean.setGender("male");
		bean.setCreatedBy("abc@gmail.com");
		bean.setModifiedBy("xyz@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}

	private static void testAdd() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setFirstName("avnish");
		bean.setLastName("upadhyay");
		bean.setLoginId("uavnish2907@gmail.com");
		bean.setPassword("avnish@2907");
		bean.setDob(sdf.parse("2007-01-29"));
		bean.setMobileNo("7648880017");
		bean.setRoleId(1);
		bean.setGender("male");
		bean.setCreatedBy("abc@gmail.com");
		bean.setModifiedBy("xyz@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

}
