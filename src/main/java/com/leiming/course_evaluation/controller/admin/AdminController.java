package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.*;
import com.leiming.course_evaluation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassService classService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private TeachingManagementService teachingManagementService;
    //管理员登录后主页面
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request){
        model.addAttribute("msg", msg);
        if (!(request.getSession().getAttribute("user") instanceof Admin)){
            return new ModelAndView("error/403");
        }
        return new ModelAndView("admin/index");
    }




    //管理员修改账号（信息，密码等）
    @PostMapping(value = "/admin")
    @ResponseBody
    public String changePassword(String old_password, String new_password) {
        Admin admin = adminService.findOneByPassword(old_password);
        if (admin != null){
            admin.setPassword(new_password);
            adminService.save(admin);
            return "ok";
        }else if (admin == null){
            return "no";
        }
        return "fail";
    }




    //学生列表获取
    @GetMapping("/students")
    @ResponseBody
    public Map<String,Object> student(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Student> content = studentService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        for (Student s:content
        ) {
            s.setDptName(s.getDepartment().getDptName());
            s.setClassName(s.getCgClass().getClassName());
        }
        map.put("data",content);
        map.put("size",studentService.findAllCount());
        return map;
    }
    //修改学生信息：页面
    @GetMapping("/editStudent")
    public ModelAndView editStudentPage(Long id,Model model){
        Student student = studentService.findById(id);
        List<Department> departmentList = departmentService.findAllList();
        List<CgClass> cgClassList = classService.findAllList();
        model.addAttribute("student",student);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("cgClassList",cgClassList);
        return new ModelAndView("admin/studentForm.html","model",model);
    }
    //方法
    @PostMapping("/editStudent")
    @ResponseBody
    public String editStudent(Student student,Long classId,Long department){
        Student studentNew = studentService.findById(student.getId());
        studentNew.setUsername(student.getUsername());
        studentNew.setStuNumber(student.getStuNumber());
        studentNew.setSex(student.getSex());
        studentNew.setDepartment(departmentService.findByID(department));
        studentNew.setCgClass(classService.findById(classId));
        studentService.saveOne(studentNew);
        return "ok";
    }
    //添加学生的界面
    @GetMapping("/addStudent")
    public ModelAndView addStudent(Model model){
        List<Department> departmentList = departmentService.findAllList();
        List<CgClass> cgClassList = classService.findAllList();

        model.addAttribute("departmentList",departmentList);
        model.addAttribute("cgClassList",cgClassList);

        return new ModelAndView("admin/studentAdd.html","model",model);
    }
    //添加学生的方法
    @PostMapping("/addStudent")
    @ResponseBody
    public String addStudent(Student student,Long classId,Long department){

        student.setPassword(student.getStuNumber());
        CgClass cgClass = classService.findById(classId);
        student.setCgClass(cgClass);
        student.setDepartment(departmentService.findByID(department));
        try{
            studentService.saveOne(student);
            return "ok";
        } catch (Exception e){
            return "no";
        }

    }

    //删除单个学生
    @PostMapping("/deleteStudent")
    @ResponseBody
    public int deleteStudent(int id){
        int i =studentService.deleteStudent(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个学生
    @PostMapping("/deleteAllStudent")
    @ResponseBody
    public int deleteAllStudent(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] stuList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : stuList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = studentService.deleteAllStudent(LString);
        return i;
    }



    //教师列表获取
    @GetMapping("/teachers")
    @ResponseBody
    public Map<String,Object> teacher(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Teacher> content = teacherService.findAll(pageable).getContent();
        System.out.println(content);
        Map<String,Object> map = new HashMap<>();
        for (Teacher s:content
        ) {
            s.setDptName(s.getDepartment().getDptName());
        }
        map.put("data",content);
        map.put("size",teacherService.findAllCount());
        return map;
    }
    //修改教师信息：页面
    @GetMapping("/editTeacher")
    public ModelAndView editTeacher(Long id,Model model){
        Teacher teacher = teacherService.findById(id);
        List<Department> departmentList = departmentService.findAllList();
        model.addAttribute("teacher",teacher);
        model.addAttribute("departmentList",departmentList);
        return new ModelAndView("admin/teacherForm.html","model",model);
    }
    //修改教师方法
    @PostMapping("/editTeacher")
    @ResponseBody
    public String editStudent(Teacher teacher,Long department){
        Teacher teacherNew = teacherService.findById(teacher.getId());
        teacherNew.setUsername(teacher.getUsername());
        teacherNew.setTeacherNumber(teacher.getTeacherNumber());
        teacherNew.setSex(teacher.getSex());
        teacherNew.setDepartment(departmentService.findByID(department));
        teacherService.saveOne(teacherNew);
        return "ok";
    }
    //添加教师的界面
    @GetMapping("/addTeacher")
    public ModelAndView addTeacher(Model model){

        List<Department> departmentList = departmentService.findAllList();
        model.addAttribute("departmentList",departmentList);


        return new ModelAndView("admin/teacherAdd.html","model",model);
    }


    //添加教师的方法
    @PostMapping("/addTeacher")
    @ResponseBody
    public String addTeacher(Teacher teacher ,Long department){
        teacher.setPassword(teacher.getTeacherNumber());
        teacher.setDepartment(departmentService.findByID(department));
        try {
            teacherService.saveOne(teacher);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }


    }
    //删除单个教师
    @GetMapping("/deleteTeacher")
    @ResponseBody
    public int deleteTeacher(int id){
        int i =teacherService.deleteTeacher(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个教师
    @GetMapping("/deleteAllTeacher")
    @ResponseBody
    public int deleteAllTeacher(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] teaList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : teaList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = teacherService.deleteAllTeacher(LString);
        return i;
    }








    //院系列表获取：页面
    @GetMapping("/departments")
    @ResponseBody
    public Map<String,Object> department(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Department> content = departmentService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",departmentService.findAllCount());
        return map;
    }
    //修改页面获取
    @GetMapping("/editDepartment")
    public ModelAndView toEditDepartment(Long id,Model model){
        Department department = departmentService.findById(id);
        model.addAttribute("department",department);
        return new ModelAndView("admin/departmentForm.html","model",model);
    }
    //方法
    @PostMapping("/editDepartment")
    @ResponseBody
    public String editDepartment(Department department){
        Department departmentNew = departmentService.findById(department.getId());
        departmentNew.setDptName(department.getDptName());
        departmentNew.setMark(department.getMark());
        departmentService.saveOne(departmentNew);
        return "ok";
    }

    //添加院系的界面
    @GetMapping("/addDepartment")
    public ModelAndView addDepartment(Model model){
        return new ModelAndView("admin/departmentAdd.html","model",model);
    }


    //添加院系的方法
    @PostMapping("/addDepartment")
    @ResponseBody
    public String addDepartment(Department department){

        try {
            departmentService.saveOne(department);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }

    //单个删除
    @GetMapping("/deleteDepartment")
    @ResponseBody
    public int deleteDepartment(int id){
        int i =departmentService.deleteDepartment(id);
        System.out.println("======"+id);
        return i;
    }
    //多个删除
    @GetMapping("/deleteAllDepartment")
    @ResponseBody
    public int deleteAllDepartment(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] depList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : depList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = departmentService.deleteAllDepartment(LString);
        return i;
    }



    //班级列表获取
    @GetMapping("/classes")
    @ResponseBody
    public Map<String,Object> class_list(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<CgClass> content = classService.findAll(pageable).getContent();

        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",classService.findAllCount());
        return map;
    }
    //修改页面获取
    @GetMapping("/editClass")
    public ModelAndView toEditClass(Long id,Model model){
        CgClass cgClass = classService.findById(id);
        model.addAttribute("cgClass",cgClass);
        return new ModelAndView("admin/classForm.html","model",model);
    }
    //方法
    @PostMapping("/editClass")
    @ResponseBody
    public String editClass(CgClass cgClass){
        CgClass cgClassNew = classService.findById(cgClass.getId());
        cgClassNew.setClassName(cgClass.getClassName());
        cgClassNew.setType(cgClass.getType());
        classService.saveOne(cgClassNew);
        return "ok";
    }
    //添加班级的界面
    @GetMapping("/addClass")
    public ModelAndView addClass(Model model){
        return new ModelAndView("admin/classAdd.html","model",model);
    }
    //添加班级的方法
    @PostMapping("/addClass")
    @ResponseBody
    public String addClass(CgClass cgClass){

        try {
            classService.saveOne(cgClass);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }
    //删除单个
    @GetMapping("/deleteClass")
    @ResponseBody
    public int deleteClass(int id){
        int i =classService.deleteClass(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个
    @GetMapping("/deleteAllClass")
    @ResponseBody
    public int deleteAllClass(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] claList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : claList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = classService.deleteAllClass(LString);
        return i;
    }




    //课程列表获取
    @GetMapping("/courses")
    @ResponseBody
    public Map<String,Object> course(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Course> content = courseService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",courseService.findAllCount());
        return map;
    }
    //修改课程列表页面
    @GetMapping("/editCourse")
    public ModelAndView toEditCourse(Long id,Model model){
        Course course = courseService.findById(id);
        model.addAttribute("course",course);
        return new ModelAndView("admin/courseForm.html","model",model);
    }
    //方法
    @PostMapping("/editCourse")
    @ResponseBody
    public String editCourse(Course course){
        Course courseNew = courseService.findById(course.getId());
        courseNew.setCourseName(course.getCourseName());
        courseNew.setMark(course.getMark());
        courseService.saveOne(courseNew);
        return "ok";
    }
    //添加课程的界面
    @GetMapping("/addCourse")
    public ModelAndView addCourse(Model model){
        return new ModelAndView("admin/courseAdd.html","model",model);
    }

    //添加课程的方法
    @PostMapping("/addCourse")
    @ResponseBody
    public String addCourse(Course cgClass){

        try {
            courseService.saveOne(cgClass);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }
    //删除单个
    @GetMapping("/deleteCourse")
    @ResponseBody
    public int deleteCourse(int id){
        int i =courseService.deleteCourse(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个
    @GetMapping("/deleteAllCourse")
    @ResponseBody
    public int deleteAllCourse(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] couList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : couList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = courseService.deleteAllCourse(LString);
        return i;
    }



    //【批次】（年级）列表获取：页面
    @GetMapping("/batches")
    @ResponseBody
    public Map<String,Object> batches(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Batch> content = batchService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",batchService.findAllCount());
        return map;
    }
    @GetMapping("/editBatch")
    public ModelAndView toEditBatch(Long id,Model model){
        Batch batch = batchService.findById(id);
        model.addAttribute("batch",batch);
        return new ModelAndView("admin/batchForm.html","model",model);
    }
    //方法
    @PostMapping("/editBatch")
    @ResponseBody
    public String editBatch(Batch batch){
        Batch batchNew = batchService.findById(batch.getId());
        batchNew.setBatchName(batch.getBatchName());
        batchNew.setStatus(batch.getStatus());
        batchService.saveOne(batchNew);
        return "ok";
    }
    //添加批次的界面
    @GetMapping("/addBatch")
    public ModelAndView addBatch(Model model){
        return new ModelAndView("admin/batchAdd.html","model",model);
    }
    //添加批次的方法
    @PostMapping("/addBatch")
    @ResponseBody
    public String addBatch(Batch batch){

        try {
            batchService.saveOne(batch);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }




    //授课管理（包括年级，批次）：页面
    @GetMapping("/teachingManagements")
    @ResponseBody
    public Map<String,Object> teachingManagements(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<TeachingManagement> content = teachingManagementService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",teachingManagementService.findAllCount());
        return map;
    }


}
