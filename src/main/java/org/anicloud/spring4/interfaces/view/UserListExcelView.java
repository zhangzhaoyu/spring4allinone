package org.anicloud.spring4.interfaces.view;

import org.anicloud.spring4.application.dto.UserDto;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyu on 15-6-11.
 */
public class UserListExcelView extends AbstractExcelView {


    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfWorkbook,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Content-Disposition", "inline;filename=" + new String("Usr List".getBytes(), "UTF-8"));
        List<UserDto> userDtoList = (List<UserDto>) map.get("userList");
        HSSFSheet sheet = hssfWorkbook.createSheet("users");

        int rowNum = 0;
        for (UserDto userDto : userDtoList) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(userDto.getUserName());
            row.createCell(1).setCellValue(userDto.getRealName());
        }
    }
}
