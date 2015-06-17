package Round2;

public class Test2 {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //int SellerEmotionalWords[];
		int[] temp = new int[3];
		temp[0]=312;
		temp[1]=423;
		temp[2]=23423;
		Test2 ttt = new Test2();
		ttt.setSellerEmotionalWords(temp);
		for(int output:ttt.getSellerEmotionalWords())
			System.out.println(output);
	}
	
	public int[] getSellerEmotionalWords() {
		return SellerEmotionalWords;
	}

	public void setSellerEmotionalWords(int[] sellerEmotionalWords) {
		SellerEmotionalWords = sellerEmotionalWords;
	}

	int SellerEmotionalWords[];
	

}
