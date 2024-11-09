package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.CollegeBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.ServletUtility;
 
@WebServlet(name = "CollegeListCtl" , urlPatterns = {"/CollegeListCtl"})
public class CollegeListCtl extends BaseCtl {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();

		try {
			List list = model.search(bean, 0, 0);
			ServletUtility.setList(list, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_LIST_VIEW;
	}
}
