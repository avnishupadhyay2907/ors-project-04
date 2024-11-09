package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "CollegeCtl", urlPatterns = { "/CollegeCtl" })
public class CollegeCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name", PropertyReader.getValue("error.require", "College Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("name"))) {

			request.setAttribute("name", PropertyReader.getValue("name", "Invalid College Name"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("address"))) {

			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("address"))) {

			request.setAttribute("address", PropertyReader.getValue("address", "Invalid Address"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("state"))) {

			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("state"))) {

			request.setAttribute("state", PropertyReader.getValue("state", "Invalid State"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("city"))) {

			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("city"))) {

			request.setAttribute("city", PropertyReader.getValue("city", "Invalid City"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {

			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "phoneNo"));
			pass = false;

		} else if (!DataValidator.isPhoneLength(request.getParameter("phoneNo"))) {

			request.setAttribute("phoneNo", "Phone No Must Have 10 Digits");
			pass = false;

		} else if (!DataValidator.isPhoneNo(request.getParameter("phoneNo"))) {

			request.setAttribute("phoneNo", "Please Correct Phone No");
			pass = false;

		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setphoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		populateDTO(bean, request);

		return null;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_VIEW;
	}

}
