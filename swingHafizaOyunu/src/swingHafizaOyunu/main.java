package swingHafizaOyunu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonListener;

import java.awt.Color;
import java.awt.SystemColor;


public class main extends JFrame {

	
private static kart [][] kartlar=new kart[4][4];	//private JButton [][] butonlar=new JButton[5][5];	
private static int deger=0;
private static String ilk_deger="";
private static JButton tutulacak=null;
private static String ikinci_deger="";
private static int sure=0;
private static String tutulacak_ilk="";
private static String tutulacak_ikinci="";
private static int hak=3;

Set<JButton> butonlarim=new LinkedHashSet<JButton>();
Set<JButton> enable_butonlarim=new LinkedHashSet<JButton>();
	
	public static void main(String[] args) {
		
		kartlar[0][0]=new kart('E');
		kartlar[0][1]=new kart('A');
		kartlar[0][2]=new kart('B');
		kartlar[0][3]=new kart('F');
		
		kartlar[1][0]=new kart('G');
		kartlar[1][1]=new kart('A');
		kartlar[1][2]=new kart('D');
		kartlar[1][3]=new kart('H');
		
		kartlar[2][0]=new kart('F');
		kartlar[2][1]=new kart('C');
		kartlar[2][2]=new kart('D');
		kartlar[2][3]=new kart('H');
		
		
		kartlar[3][0]=new kart('E');
		kartlar[3][1]=new kart('G');
		kartlar[3][2]=new kart('B');
		kartlar[3][3]=new kart('C');
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	
	public main() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setResizable(false);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(4,4));
		BasicButtonListener btnListener = new BasicButtonListener();
		setContentPane(contentPane);
		
		
	    
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				JButton buton=new JButton();
				buton.setName(String.valueOf(kartlar[i][j].getHarf())+" "+String.valueOf(i)+" "+String.valueOf(j));
				contentPane.add(buton);
				buton.addActionListener(btnListener);
				butonlarim.add(buton);
				
				
				
			}
		}
	
	}
	private class BasicButtonListener implements ActionListener {

	      @Override
	      public void actionPerformed(ActionEvent e) {
	         JButton btn = (JButton)e.getSource(); 
	         String ad = btn.getName().trim(); 
	         
	         String[] parts = ad.split(" ");
	         String part1 = parts[0];   //Harfi alýr.
	         String part2 = parts[1];   //indeks 0
	         String part3=  parts[2];   //indeks 1
	         
	        
	         
	         if(ilk_deger.equals(""))  //1.degeri al
	         {
	        	 ilk_deger=part1;
	        	 tutulacak=btn;
	        	 tutulacak_ilk=part2;
	        	 tutulacak_ikinci=part3;
	        	 btn.setText(part1);
	        	 btn.setEnabled(false);
	        	 btn.setFont(new Font("Arial", Font.PLAIN, 40));
	        	 btn.setForeground(Color.white);
	        	 btn.setBackground(Color.black);
	        	 deger++;
	        	 return;
	         }
	         if(deger==1 && !ilk_deger.equals(""))  //2.degeri al.
	         {
	        	 ikinci_deger=part1;
	        	 btn.setText(part1);
	        	 btn.setEnabled(false);
	        	 btn.setFont(new Font("Arial", Font.PLAIN, 40));
	        	 btn.setForeground(Color.white);
	        	 btn.setBackground(Color.black);
	        	 deger++;
	        	
	       
	         }
	        
	     
	         if(deger==2)
	         {
	        	 
	        	for(JButton butonlar:butonlarim)
	        	{
	        		
	        		if(butonlar.isEnabled()==true)
	        		{
	        			
	        			enable_butonlarim.add(butonlar);
	        		}
	        	}
	        	
	        	for(JButton aktifler:enable_butonlarim)
	        	{
	        		
	        		aktifler.setEnabled(false);
	        	}
	        	
	        	boolean karsilastirma_sonucu= karsilastir(ilk_deger, ikinci_deger);
	        	
	        	if(!karsilastirma_sonucu)
	        	{
	        		 Timer timer=new Timer(1000,null);
		        	 timer.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							sure++;
							if(sure==3)
							{
								timer.stop();
								sure=0;
								tutulacak.setText("");
								tutulacak.setEnabled(true);
								tutulacak.setBackground(null);
								btn.setBackground(null);
								tutulacak=null;
								btn.setText("");
								btn.setEnabled(true);
								for(JButton aktifler:enable_butonlarim)
					        	{
					        		aktifler.setEnabled(true);
					        	}
								
							
							}
							
						}
					});timer.start();
	        		
	        		hak--;
	        	}
	        	else
	        	{
	        		
	        		
	        		kartlar[Integer.valueOf(part2)][Integer.valueOf(part3)].setDurum(true);
	        		kartlar[Integer.valueOf(tutulacak_ilk)][Integer.valueOf(tutulacak_ikinci)].setDurum(true);
	        		
	        		for(JButton aktifler:enable_butonlarim)
		        	{
		        		aktifler.setEnabled(true);
		        	}
	        		tutulacak.setEnabled(false);
	        		btn.setEnabled(false);
	        		butonlarim.remove(btn);
	        		butonlarim.remove(tutulacak);
	        		if(enable_butonlarim.contains(btn))
	        			enable_butonlarim.remove(btn);
	        		if(enable_butonlarim.contains(tutulacak))
	        			enable_butonlarim.remove(tutulacak);
	        		
	        	}
	        	ilk_deger="";
				ikinci_deger="";
				tutulacak_ilk="";
        		tutulacak_ikinci="";
        		
	        	
	        	
	        	deger=0;
	        	
	        	if(hak==0)
	        	{
	        		JOptionPane.showMessageDialog(null,"Hakkýnýz Bitti.Oyun sona erdi..");
	        		System.exit(0);
	        	}
	        	
	        	boolean oyunDurumu=oyunBittimi();
	        	if(oyunDurumu)
	        		JOptionPane.showMessageDialog(null,"Oyun Bitti");
	         }
	   
	         }

	      }
	
	private boolean oyunBittimi()
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(!kartlar[i][j].isDurum())
					return false;
			}
	   }
		return true;
	}
	   
private boolean karsilastir(String harf1,String harf2)
{
	
	if(harf1.equals(harf2))
		return true;
	else
	return false;
}
}

