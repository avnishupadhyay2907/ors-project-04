package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "CourseCtl", urlPatterns = { "/CourseCtl" })
public class CourseCtl extends BaseCtl {

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
		if (DataValidator.isNull(request.getParameter("duration"))) {

			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("duration"))) {

			request.setAttribute("duration", PropertyReader.getValue("duration", "Invalid Duration"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("description"))) {

			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("description"))) {

			request.setAttribute("description", PropertyReader.getValue("description", "Invalid Description"));
			pass = false;

		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CourseBean bean = new CourseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

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
		return ORSView.COURSE_VIEW;
	}

}
