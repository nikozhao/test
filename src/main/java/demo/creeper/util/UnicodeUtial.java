package demo.creeper.util;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/29/18
 * @Email:
 */
public class UnicodeUtial {

    public static String decode(String target) {
        String s="";
        try {
            byte[] converttoBytes = target.getBytes("UTF-8");
            s=new String(converttoBytes, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public static String decodeAll(String content){
        int index =0;
        StringBuffer sb = new StringBuffer();
        while (content.indexOf("/u")!=-1){
            index = content.indexOf("/u");
            sb.append(content.substring(0,index));
            sb.append(UnicodeUtial.decode(content.substring(index,index+6)));
            content=content.substring(index+6);
        }
        sb.append(content);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception{
       // String s =UnicodeUtial.decode("\u503a");
        String s="{\"data\":{\"f000697\":{\"name\":\"\u6c47\u6dfb\u5bcc\u79fb\u52a8\u4e92\u8054\u80a1\u7968\",\"scale\":\"46.7904\",\"fundType\":\"gpx\",\"tzfg\":\"\\u5e73\\u8861\\u578b\",\"manager\":\"\\u6b27\\u9633\\u6c81\\u6625\",\"code\":\"000697\",\"enddate\":\"2018-03-28\",\"nowweek\":\"8.60\",\"nowyear\":\"14.48\",\"week\":\"1.03\",\"month\":\"3.57\",\"tmonth\":\"15.02\",\"hyear\":\"0.96\",\"year\":\"3.57\",\"now\":\"47.80\",\"prerate\":\"-0.81\",\"twoyear\":\"-8.48\",\"tyear\":\"-30.64\",\"fyear\":\"47.80\",\"updatetime\":\"2018-03-29 05:16:19\",\"fxdj\":\"\\u504f\\u9ad8\\u98ce\\u9669\",\"zkl\":\"0.1\",\"sgoldfl\":\"1.5\",\"sgfl\":\"0.15\",\"wedfl\":\"0.15\",\"saletype\":\"\\u80a1\\u7968\\u578b\",\"net\":\"1.4780\",\"totalnet\":\"1.4780\",\"sydate\":\"2018-03-28\",\"cxthree\":\"1\",\"szthree\":\"1\",\"yhthree\":\"0\",\"htstar\":\"0\",\"jastar\":\"1\",\"rgStart\":\"2014-07-28\",\"rgEnd\":";
        System.out.println(decodeAll(s));
    }
}
