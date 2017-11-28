package com.mine.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Answer;
import com.mine.model.Question;
import com.mine.model.Survey;
import com.mine.service.SurveyService;

@Controller
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey> {

	private static final long serialVersionUID = -805682647288444348L;

	@Resource(name = "surveyServiceImpl")
	private SurveyService surveyService;

	private Integer sid;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String execute() {

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public InputStream getIs() {
		try {
			Map<Integer, Short> qidIndexMap = new HashMap<Integer, Short>();

			List<Question> qlist = surveyService.getQuestions(sid);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("收集调查");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			for (short i = 0; i < qlist.size(); i++) {
				Question q = qlist.get(i);
				cell = row.createCell(i);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(q.getTitle());
			    sheet.setColumnWidth(i, (short)6000 );
				qidIndexMap.put(q.getId(), i);
			}

			List<Answer> alist = surveyService.getAnswers(sid);
			int rcount = 0;
			String oldUuid = "";
			String newUuid = "";
			for (Answer a : alist) {
				newUuid = a.getUuid();
				if (!newUuid.equals(oldUuid)) {
					rcount++;
					row = sheet.createRow(rcount);
					oldUuid = newUuid;
				}
				cell = row.createCell(qidIndexMap.get(a.getQuestionId()));
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(a.getAnswerIds());
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
