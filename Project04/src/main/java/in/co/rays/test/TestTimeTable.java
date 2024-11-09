package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.TimetableBean;
import in.co.rays.model.TimetableModel;

public class TestTimeTable {

	public static void main(String[] args) throws Exception {

		 //testAdd();
		// testUpdate();
		// testDelete();
		 //testSearch();
		//testfindByPk();
		testfindBySemester();
	}

	private static void testfindBySemester() throws Exception {
		
		TimetableBean bean = new TimetableBean();

		TimetableModel model = new TimetableModel();

		bean = model.findBySemester("1");

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getSemester());
			System.out.println(bean.getDescription());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
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
		TimetableBean bean = new TimetableBean();

		TimetableModel model = new TimetableModel();

		bean = model.findByPk(1);

		if (bean != null) {


			System.out.println(bean.getId());
			System.out.println(bean.getSemester());
			System.out.println(bean.getDescription());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());

			System.out.println("Pk Already Exist");

		} else {

			System.out.println("Pk Not Exist");

		}
	}

	private static void testSearch() throws Exception {
		TimetableBean bean = new TimetableBean();

		TimetableModel model = new TimetableModel();

		bean.setSemester("1");

		List list = model.search(bean, 0, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (TimetableBean) it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getSemester());
			System.out.println(bean.getDescription());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedDateTime());

		}
	}

	private static void testDelete() throws Exception {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();

		bean.setId(2);
		model.delete(bean);
	}

	private static void testAdd() throws Exception {

		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setSemester("1");
		bean.setDescription("first year");
		bean.setExamDate(sdf.parse("2024-10-05"));
		bean.setExamTime("09:00:00");
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("abc@gmail.com");
		bean.setModifiedBy("xyz@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
bean.setId(1);
		bean.setSemester("1");
		bean.setDescription("first year");
		bean.setExamDate(sdf.parse("2024-10-05"));
		bean.setExamTime("09:00:00");
		bean.setCourseId(1);
		bean.setCourseName("b_tech cs");
		bean.setSubjectId(1);
		bean.setSubjectName("java");
		bean.setCreatedBy("abc@gmail.co");
		bean.setModifiedBy("xyz@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}
}
