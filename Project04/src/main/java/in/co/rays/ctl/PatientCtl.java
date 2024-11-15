package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.PatientModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "PatientCtl", urlPatterns = { "/PatientCtl" })
public class PatientCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Patient Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid  Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dateOfVisit"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date of visit"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Invalid Mobile No");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", PropertyReader.getValue("error.require", "disease"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("disease"))) {
			request.setAttribute("disease", "disease is not applicable");
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PatientBean bean = new PatientBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));
		System.out.println("date simple => " + request.getParameter("dateOfVisit"));
		bean.setDateOFVisit(DataUtility.getDate(request.getParameter("dateOfVisit")));
		System.out.println("date => " + bean.getDateOFVisit());
		bean.setMobile(DataUtility.getString(request.getParameter("mobile")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		PatientModel model = new PatientModel();

		PatientBean bean = (PatientBean) populateBean(request);
		System.out.println("in do post of patrientttttttttttttt");
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			try {

				if (bean.getId() != 0 && bean.getId() > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Patient updated Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				} else {
					model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Patient Added Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Patient  already exist", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bean", new PatientBean());

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			PatientModel model = new PatientModel();

			try {
				PatientBean bean = model.findByPk(id);
				System.out.println("Bean class in request scope: " + bean.getClass().getName());

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
		return ORSView.PATIENT_VIEW;
	}

}
