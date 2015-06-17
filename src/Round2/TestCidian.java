package Round2;

import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;
import java.util.*;
public class TestCidian {
	
	public static int[] CalculateEmotionalWords(String OneSentence){
		SegTag st = new SegTag(1);
		SegResult sr = st.split(OneSentence);
		String results[] = sr.getFinalResult().split(" ");
        ArrayList<String> sliptResult = new ArrayList<String>();
        for(int i=0;i<results.length;i++){
        	String[] tem = results[i].split("/");
        	sliptResult.add(tem[0]);
        }
        int[] countEmotionalWords = {0,0,0,0,0,0,0,0};//前面六个是emotionalwords的count，最后一个是分词的值，就是这句话里面，分出来多少个词
        
        countEmotionalWords[7]= sliptResult.size();
        	
        ReadFileFromTxt.GenerateChineseEmotionalWordsList();//执行此方法，填充七个arrayList
        
        for(String temp:sliptResult){
        	if(ReadFileFromTxt.ZhuzhangGanzhi.contains(temp.trim()))countEmotionalWords[0]++;
        	if(ReadFileFromTxt.ZhuzhangRenwei.contains(temp.trim()))countEmotionalWords[1]++;
        	if(ReadFileFromTxt.ZhengmianQinggan.contains(temp.trim()))countEmotionalWords[2]++;
        	if(ReadFileFromTxt.ZhengmianPingjia.contains(temp.trim()))countEmotionalWords[3]++;
        	if(ReadFileFromTxt.Chengdu.contains(temp.trim()))countEmotionalWords[4]++;
        	if(ReadFileFromTxt.FumianQinggan.contains(temp.trim()))countEmotionalWords[5]++;
        	if(ReadFileFromTxt.FumianPingjia.contains(temp.trim()))countEmotionalWords[6]++;
        }
        
        return countEmotionalWords;
        
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //切词
		
		int[] Results = TestCidian.CalculateEmotionalWords("你好,我喜欢这个商品,能够给我打折吗？ 点点滴滴, 钟爱 .发觉.耳闻.看待.稍许.恻隐.干干巴巴 ");
		for(int test:Results){
			System.out.println(test);
		}
		
		/*
		SegTag st = new SegTag(1);
		SegResult sr = st.split("你好,我喜欢这个商品,能够给我打折吗？ 点点滴滴, 钟爱 .发觉.耳闻.看待.稍许.恻隐.干干巴巴 ");
        String results[] = sr.getFinalResult().split(" ");
        ArrayList<String> sliptResult = new ArrayList<String>();
        for(int i=0;i<results.length;i++){
        	String[] tem = results[i].split("/");
        	sliptResult.add(tem[0]);
        }
		//计算情感词数组
        //定义一个七维数组，计算每类词语出现的数目
        int[] countEmotionalWords = {0,0,0,0,0,0,0};
        //顺序分别为：ZhuzhangGanzhi; ZhuzhangRenwei; ZhengmianQinggan; ZhengmianPingjia; Chengdu; FumianQinggan; FumianPingjia
        ReadFileFromTxt.GenerateChineseEmotionalWordsList();//执行此方法，填充七个arrayList
        for(String temp:sliptResult){
        	System.out.println(temp);
        	if(ReadFileFromTxt.ZhuzhangGanzhi.contains(temp.trim()))countEmotionalWords[0]++;
        	if(ReadFileFromTxt.ZhuzhangRenwei.contains(temp.trim()))countEmotionalWords[1]++;
        	if(ReadFileFromTxt.ZhengmianQinggan.contains(temp.trim()))countEmotionalWords[2]++;
        	if(ReadFileFromTxt.ZhengmianPingjia.contains(temp.trim()))countEmotionalWords[3]++;
        	if(ReadFileFromTxt.Chengdu.contains(temp.trim()))countEmotionalWords[4]++;
        	if(ReadFileFromTxt.FumianQinggan.contains(temp.trim()))countEmotionalWords[5]++;
        	if(ReadFileFromTxt.FumianPingjia.contains(temp.trim()))countEmotionalWords[6]++;
        }
        for(int i=0;i<7;i++){
        	System.out.println("Index "+i+":  "+countEmotionalWords[i]);
        }
        
        */
	}

}
