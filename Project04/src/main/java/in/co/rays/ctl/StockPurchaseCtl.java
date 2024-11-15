package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.StockPurchaseBean;
import in.co.rays.bean.WishlistBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StockPurchaseModel;
import in.co.rays.model.WishlistModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "StockPurchaseCtl", urlPatterns = { "/StockPurchaseCtl" })
public class StockPurchaseCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "Quantity "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("purchasePrice"))) {
			request.setAttribute("purchasePrice", PropertyReader.getValue("error.require", "Purchase Price"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "Purchase Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("OrderType"))) {
			request.setAttribute("orderType", PropertyReader.getValue("error.require", "Order Type"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		StockPurchaseBean bean = new StockPurchaseBean();


		System.out.println("requesttttidddddd=>" + request.getParameter("id"));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println("beannnnnnnnniddddddd=>" + bean.getId());

		System.out.println("requesttttDessssss=>" + request.getParameter("quantity"));
		bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
		System.out.println("beannnnnnnnnnDesssss=>" + bean.getQuantity());

		System.out.println("reqestrtttt expppppppppp=>" + request.getParameter("purchasePrice"));
		bean.setPurchasePrice(DataUtility.getString(request.getParameter("purchasePrice")));
		System.out.println("beannnnnnnnnnExpppppp" + bean.getPurchasePrice());
	
		System.out.println("date simple => " + request.getParameter("purchaseDate"));
		bean.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));
		System.out.println("date => " + bean.getPurchaseDate());

		System.out.println("reqqqqqqqqqqqqComnnnnnnnnnnn=>" + request.getParameter("OrderType"));
		bean.setOrderType(DataUtility.getString(request.getParameter("OrderType")));
		System.out.println("beannnnnnnnnConnnnnnn" + bean.getOrderType());

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("in do post method");
		String op = DataUtility.getString(request.getParameter("operation"));

		StockPurchaseModel model = new StockPurchaseModel();

		StockPurchaseBean bean = (StockPurchaseBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			try {

				if (bean.getId() != 0 && bean.getId() > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Stock Purchase updated Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				} else {
					model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Stock Purchase Added Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Order Type  already exist", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STOCK_PURCHASE_CTL, request, response);
			return;
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do get method");
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			StockPurchaseModel model = new StockPurchaseModel();

			try {
				StockPurchaseBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.STOCKPURCHASE_VIEW;
	}

}
