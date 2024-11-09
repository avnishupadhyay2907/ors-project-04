package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.WishlistBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.WishlistModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "WishlistCtl", urlPatterns = { "/WishlistCtl" })
public class WishlistCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "Product Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("product"))) {
			request.setAttribute("product", "Invalid Product Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "User Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("userName"))) {
			request.setAttribute("userName", "Invalid User Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("remark"))) {
			request.setAttribute("remark", PropertyReader.getValue("error.require", "Remark"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("remark"))) {
			request.setAttribute("remark", "Remark is not applicable");
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		WishlistBean bean = new WishlistBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setProduct(DataUtility.getString(request.getParameter("product")));
		System.out.println("date simple => " + request.getParameter("date"));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		System.out.println("date => " + bean.getDate());
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setRemark(DataUtility.getString(request.getParameter("remark")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		WishlistModel model = new WishlistModel();

		WishlistBean bean = (WishlistBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			try {

				if (bean.getId() != 0 && bean.getId() > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Wishlist updated Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				} else {
					model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Wishlist Added Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("product  already exist", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WISHLIST_CTL, request, response);
			return;
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ljhlkuglkhkjlhghjg");
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			WishlistModel model = new WishlistModel();

			try {
				WishlistBean bean = model.findByPk(id);
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
		System.out.println("in getView method");
		// TODO Auto-generated method stub
		return ORSView.WISHLIST_VIEW;
	}

}
