package recognition.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import recognition.bean.Commodity;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class Main_controller {

    @RequestMapping("/index")
    public String index() {
        /*try{
            Process pr = Runtime.getRuntime().exec("python /home/wang/a.py");

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return "test";
    }

    @ResponseBody
    @RequestMapping(value="/upload_picture",produces="application/json;charset=UTF-8")
    public String upload_picture(@RequestParam(value="file",required=false)MultipartFile file){
        /*Commodity commodity=new Commodity();
        commodity.setImage_url("a.jpg");
        commodity.setPrice("15");
        commodity.setTittle("棉衣");
        commodity.setStore_url("123");
        List<Commodity>commodityList=new ArrayList<>();
        commodityList.add(commodity);
        JSONArray jsonObject=JSONArray.fromObject(commodityList);
        return jsonObject.toString();*/
        String result=null;
        String path="/home/ubuntu/project/tmp_image/";
        String time=String.valueOf(System.currentTimeMillis());
        String image_path=null;
        try{
            String contentType=file.getContentType();
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            image_path=path+time+"."+imageName;
            file.transferTo(new File(image_path));
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            Socket socket = new Socket("127.0.0.1", 8017);
            /*DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(image_path);
            // 接收服务器的返回数据
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("hserver:" + in.readByte());*/
            PrintWriter printWriter= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
            printWriter.write(image_path);
            printWriter.flush();
            socket.shutdownOutput();

            InputStream inputStream=socket.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            result=bufferedReader.readLine();
            /*System.out.println(result);*/
            printWriter.close();
            bufferedReader.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        /*try{
            String s;
            Runtime runtime= Runtime.getRuntime();
            Process pr = runtime.exec("python /home/wang/学习资料/ene/get_attribute.py "+image_path);
            System.out.println("python /home/wang/学习资料/ene/get_attribute.py "+image_path);
            pr.waitFor();
            *//*InputStream fis = pr.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            while((s=bufferedReader.readLine()) != null) {
                System.out.println(s);
            }*//*
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/te",produces="application/json;charset=UTF-8")
    public String test(@RequestParam(value="file",required=false)MultipartFile file){
        String result=null;
        String path="/home/ubuntu/project/tmp_image/";
        String time=String.valueOf(System.currentTimeMillis());
        String image_path=null;
        try{
            String contentType=file.getContentType();
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            image_path=path+time+"test"+"."+imageName;
            file.transferTo(new File(image_path));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

}
