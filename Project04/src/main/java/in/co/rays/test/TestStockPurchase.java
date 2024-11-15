package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.PositionBean;
import in.co.rays.bean.StockPurchaseBean;
import in.co.rays.model.PositionModel;
import in.co.rays.model.StockPurchaseModel;

public class TestStockPurchase {

	public static void main(String[] args) throws Exception {

		//testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		//testFindByPk();
	testFindByPurchasePrice();

	}

	private static void testFindByPurchasePrice() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		StockPurchaseModel model = new StockPurchaseModel();

		bean = model.findByPurchasePrice("450");
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getQuantity());
			System.out.println(bean.getPurchasePrice());
			System.out.println(bean.getPurchaseDate());
			System.out.println(bean.getOrderType());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
			
			System.out.println("purchase price already exist");
		} else {
			System.out.println("id not exist");
		}

	}

	private static void testFindByPk() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		StockPurchaseModel model = new StockPurchaseModel();

		bean = model.findByPk(1);
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getQuantity());
			System.out.println(bean.getPurchasePrice());
			System.out.println(bean.getPurchaseDate());
			System.out.println(bean.getOrderType());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
			
			System.out.println("purchase price already exist");
		} else {
			System.out.println("purchase price not exist");
		}

	}

	private static void testSearch() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		StockPurchaseModel model = new StockPurchaseModel();

		bean.setQuantity("1100");

		List list = model.search(bean, 0, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (StockPurchaseBean) it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getQuantity());
			System.out.println(bean.getPurchasePrice());
			System.out.println(bean.getPurchaseDate());
			System.out.println(bean.getOrderType());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());
		}

	}

	private static void testDelete() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		StockPurchaseModel model = new StockPurchaseModel();

		bean.setId(2);
		model.delete(bean);

	}

	private static void testUpdate() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		StockPurchaseModel model = new StockPurchaseModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setQuantity("1100");
		bean.setPurchasePrice("450");
		bean.setPurchaseDate(sdf.parse("2022-01-29"));
		bean.setOrderType("market");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy(null);
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(null);
		bean.setId(1);

		model.update(bean);
	}

	private static void testAdd() throws Exception {

		StockPurchaseBean bean = new StockPurchaseBean();
		StockPurchaseModel model = new StockPurchaseModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setQuantity("4000");
		bean.setPurchasePrice("91");
		bean.setPurchaseDate(sdf.parse("2022-01-29"));
		bean.setOrderType("market");
		bean.setCreatedBy("uavnish2907@gmail.com");
		bean.setModifiedBy(null);
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(null);

		model.add(bean);

	}

	
}
