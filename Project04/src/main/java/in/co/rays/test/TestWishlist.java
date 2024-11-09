package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.WishlistBean;
import in.co.rays.model.WishlistModel;

public class TestWishlist {

	public static void main(String[] args) throws Exception {

		// testAdd();
		testUpdate();
		// testDelete();
		// testSearch();
		// testFindByPk();
		// testFindByUserName();

	}

	private static void testFindByUserName() throws Exception {
		WishlistBean bean = new WishlistBean();

		WishlistModel model = new WishlistModel();
		bean = model.findByUserName("avnish");
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getProduct());
			System.out.println(bean.getDate());
			System.out.println(bean.getUserName());
			System.out.println(bean.getRemark());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
			System.out.println("login id already exist");
		} else {
			System.out.println("login id not exist");
		}

	}

	private static void testFindByPk() throws Exception {
		WishlistBean bean = new WishlistBean();

		WishlistModel model = new WishlistModel();
		bean = model.findByPk(1);
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getProduct());
			System.out.println(bean.getDate());
			System.out.println(bean.getUserName());
			System.out.println(bean.getRemark());
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

		WishlistBean bean = new WishlistBean();

		WishlistModel model = new WishlistModel();

		bean.setUserName("uavnish2907@gmail.com");

		List list = model.search(bean, 0, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (WishlistBean) it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getProduct());
			System.out.println(bean.getDate());
			System.out.println(bean.getUserName());
			System.out.println(bean.getRemark());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		}

	}

	private static void testDelete() throws Exception {
		WishlistBean bean = new WishlistBean();
		WishlistModel model = new WishlistModel();

		bean.setId(2);
		model.delete(bean);

	}

	private static void testUpdate() throws Exception {
		WishlistBean bean = new WishlistBean();
		WishlistModel model = new WishlistModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setProduct("Ram");
		bean.setDate(sdf.parse("2007-01-29"));
		bean.setUserName("avnish");
		bean.setRemark("avnish@2907");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy("");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		bean.setId(2);
		model.update(bean);
	}

	private static void testAdd() throws Exception {

		WishlistBean bean = new WishlistBean();
		WishlistModel model = new WishlistModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setProduct("avnish");
		bean.setDate(sdf.parse("2007-01-29"));
		bean.setUserName("avnish");
		bean.setRemark("avnish@2907");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy("");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}
}
