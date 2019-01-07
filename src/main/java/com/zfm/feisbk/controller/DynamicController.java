package com.zfm.feisbk.controller;


import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.BlogDO;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.DynamicService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: zhouchi
 * @Description:
 * @Date: Created in 3:42 PM 2018/12/23
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicController {
    public static final String USER_IMG_UPLOAD_PATH = "/static/blogimgs/";

    private Logger logger = LoggerFactory.getLogger(DynamicController.class);

    @Autowired
    private DynamicService dynamicService;

    @RequestMapping(path = "/init.action", method = RequestMethod.GET)
    public NormalResultDTO getBlogList(HttpSession session){
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        UserDO user = null;
        Long id = (Long) session.getAttribute("userid");
//        Long id = 39l;

        try{
            user = dynamicService.findById(id);
            List<Map<String, Object>> blogList = dynamicService.getBlogList(user);

            //对bloglist对创建时间进行逆序排序
            Collections.sort(blogList, new Comparator<Map>() {
                @Override
                public int compare(Map o1, Map o2) {
                    long c1 = Long.valueOf(o1.get("createTime").toString());
                    long c2 = Long.valueOf(o2.get("createTime").toString());
                    if(c1 > c2){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            });

            result.setCode("0000");
            result.setMessage("successful");
            result.setData(blogList);
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/info.action", method = RequestMethod.POST)
    public NormalResultDTO getSpecificUserBlog(HttpSession session, String id){
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        UserDO user = null;
        Long userid;

        if(id == null || id.isEmpty()){
            userid = (Long) session.getAttribute("userid");
        }else {
            userid = Long.parseLong(id);
        }

        try{
            user = dynamicService.findById(userid);
//            List<BlogDO> blogList = dynamicService.getSpecificBlogList(user);
            Map<String, Object> userInfo = dynamicService.getSpecificUserInfo(user);
            result.setCode("0000");
            result.setMessage("successful");
            result.setData(userInfo);
//            result.setData(blogList);
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/add.action", method = RequestMethod.POST)
    public NormalResultDTO postBlog(HttpSession session, String contentTest, String contentImage) {
        NormalResultDTO result = new NormalResultDTO("9999", "unknow error", null);
//        Long id = (Long) session.getAttribute("userid");
        Long id = (Long) session.getAttribute("userid");
        UserDO user = null;

        try {
            user = dynamicService.findById(id);

            BlogDO blog = new BlogDO();
            blog.setContentTest(contentTest);
            blog.setContentImage(contentImage);
            blog.setCreateTime(System.currentTimeMillis());
            blog.setModifyTime(blog.getCreateTime());

            NormalResultDTO postResult = dynamicService.postBlog(user, blog);
            if (postResult.getCode().equals("0000")){
                result.setCode("0000");
                result.setMessage("successful");
            }
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/upload.action", method = RequestMethod.POST)
    public NormalResultDTO postImage(HttpSession session, MultipartFile img){
        NormalResultDTO result = new NormalResultDTO("9999", "unknow error", null);
        if (img == null || img.isEmpty() || StringUtils.isBlank(img.getOriginalFilename())) {
            result.setMessage("图片为空");
            return result;
        }
        String extname = FilenameUtils.getExtension(img.getOriginalFilename());
        try {

            // 获取项目所在绝对路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()/* "D:\\workspace\\web-workspace2\\Gleaning\\src\\main\\resources" */;

            Long id = (Long) session.getAttribute("userid");
//            Long id = (Long) session.getAttribute("userid");
            Long timeStamp = System.currentTimeMillis();
            String url = path + USER_IMG_UPLOAD_PATH +  id.toString() + '_' + timeStamp.toString();
            img.transferTo(new File(url + '.' + extname));

            result.setCode("0000");
            result.setMessage("successful");
            result.setData("blogimgs/" +  id.toString() + '_' + timeStamp.toString() + '.' + extname);

        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
