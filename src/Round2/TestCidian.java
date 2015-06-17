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
        int[] countEmotionalWords = {0,0,0,0,0,0,0,0};//ǰ��������emotionalwords��count�����һ���Ƿִʵ�ֵ��������仰���棬�ֳ������ٸ���
        
        countEmotionalWords[7]= sliptResult.size();
        	
        ReadFileFromTxt.GenerateChineseEmotionalWordsList();//ִ�д˷���������߸�arrayList
        
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
        //�д�
		
		int[] Results = TestCidian.CalculateEmotionalWords("���,��ϲ�������Ʒ,�ܹ����Ҵ����� ���ε�, �Ӱ� .����.����.����.����.����.�ɸɰͰ� ");
		for(int test:Results){
			System.out.println(test);
		}
		
		/*
		SegTag st = new SegTag(1);
		SegResult sr = st.split("���,��ϲ�������Ʒ,�ܹ����Ҵ����� ���ε�, �Ӱ� .����.����.����.����.����.�ɸɰͰ� ");
        String results[] = sr.getFinalResult().split(" ");
        ArrayList<String> sliptResult = new ArrayList<String>();
        for(int i=0;i<results.length;i++){
        	String[] tem = results[i].split("/");
        	sliptResult.add(tem[0]);
        }
		//������д�����
        //����һ����ά���飬����ÿ�������ֵ���Ŀ
        int[] countEmotionalWords = {0,0,0,0,0,0,0};
        //˳��ֱ�Ϊ��ZhuzhangGanzhi; ZhuzhangRenwei; ZhengmianQinggan; ZhengmianPingjia; Chengdu; FumianQinggan; FumianPingjia
        ReadFileFromTxt.GenerateChineseEmotionalWordsList();//ִ�д˷���������߸�arrayList
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
