import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Dentaku extends JFrame implements ActionListener{
  JLabel caption = new JLabel("Java 電卓", JLabel.CENTER);
  JLabel caption2 = new JLabel("履歴",JLabel.CENTER);
  JTextField kekka = new JTextField("0"); // 計算結果を表示するテキストフィールド
  JTextField kekka_middle = new JTextField("途中式"); // 計算の途中式を表示するテキストフィールド
  //JTextField data[] = new JTextField[10];
  JTextField rireki1 = new JTextField("rireki1");
  JTextField rireki2 = new JTextField("rireki2");
  String[] bTxt={"CE","C","←","÷","7","8","9","×","4","5","6","-","1","2","3","+","+/-","0",".","="}; // ボタンの追加はこの行
  String enzan = "=";
  String middle = "Clear"; //途中式を格納する
  //String Data[] = new String[10]; //過去の履歴を格納する用
  double num, total;
  int num_output;
  String Decimal = "F";
  int decimal_cnt = 0;
  int i;
  String before_middle="---";
  String before2_middle="---";
  public Dentaku() { // 画面レイアウトの作成
    setTitle("簡易ー電卓");
    setSize(500,500);//横縦
    setLocation(100,100);//(縦,横)
    JPanel panel1 = new JPanel(new GridLayout(3,1)); // 2行 1列
    JPanel panel2 = new JPanel(new GridLayout(5,4,8,8)); // 5行 4列 間隔 8
    JPanel panel3 = new JPanel(new GridLayout(5,2)); // 2行 1列
    caption.setForeground(Color.magenta);
    caption.setFont(new Font(Font.SERIF, Font.BOLD, 28));
    panel1.add(caption);
    panel1.add(kekka_middle);
    //Arrays.fill(Data,"　");

    Font font = new Font(Font.MONOSPACED, Font.BOLD, 32);
    kekka.setEditable(false);
    kekka.setHorizontalAlignment(JTextField.RIGHT);
    kekka.setFont(font);
    kekka.setBackground(Color.white);
    panel1.add(kekka);

    //for(i = 0; i < 10 ; i++){panel3.add(data[i]);}
    panel3.add(caption2);
    panel3.add(rireki1);
    panel3.add(rireki2);

    for(i = 0; i < 20; i++) {
      JButton b = new JButton(bTxt[i]);
      b.addActionListener(this);
      b.setFont(font);
      b.setBackground(Color.white);
      if( i % 4==3 || i==16 || i < 3 || i > 17 ) b.setForeground(Color.blue); //演算記号は青
      panel2.add(b);
    }

    add(panel1, BorderLayout.NORTH);
    add(panel2, BorderLayout.CENTER);
    add(panel3, BorderLayout.SOUTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ×ボタンで終了する
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) { // ボタンが押されたときの処理
    String x = ((JButton)e.getSource()).getText(); // 押されたボタンのテキストを取得
    if( Character.isDigit(x.charAt(0)) ){ // 数字ボタン
      if(Decimal == "T"){
        num = Double.parseDouble(x) / 10 + num;
        kekka.setText(String.valueOf(num));
      }else{
        num = num * 10 + Double.parseDouble(x);
        num_output = (int)num;
        kekka.setText(String.valueOf(num_output));

      }
      //kekka.setText(String.valueOf(num));
    }else if( x=="." ){ // 小数点ボタン
      Decimal = "T";
      decimal_cnt++;
      kekka.setText(String.valueOf(num_output + x));
    }else if( x=="C" ){ // 全クリアボタン
      num = total = 0;
      Decimal = "F";
      kekka.setText("0");
      middle = "Clear";
      kekka_middle.setText(middle);
      Decimal = "F";
      decimal_cnt = 0;
      enzan = "=";
    }else if( x =="CE" ){ // 一つクリアボタン
      num = 0;
      kekka.setText("0");
      decimal_cnt = 0;
      Decimal = "F";
      //kekka_middle.setText(middle);

    }else if( x =="←" ){ // 一つ戻るボタン
      num = 0;
      kekka.setText("0");
    }else if( x =="+/-" ){ // + or - を指定するボタン

    }
    else{ // 演算ボタン
      if( enzan=="=" ){ total = num;}
      else if( enzan=="+" ) {total += num;}
      else if( enzan=="-" ) {total -= num;}
      else if( enzan=="×" ) {total *= num;}
      else if( enzan=="÷" ) {
        if(num == 0)kekka.setText("エラー");
        total /= num;
      }
      //←ここにコードを追加すること
      kekka.setText(String.valueOf(total));
      if(x == "="){
        if(middle == "Clear"){
          middle = String.valueOf(num) + x;
        }else{
          middle = middle + String.valueOf(num) + x + total;
        }
      }else{
        if(middle == "Clear"){
          middle = String.valueOf(num) + x;
        }else{
          middle = middle + String.valueOf(num) + x;
        }
    }
      kekka_middle.setText(middle);
      if( x == "=" ){
        num = total;
        /*
        //履歴にデータを格納したい
        for(i=9;i <0;i++){
          Data[i]=Data[i-1];
        }
        Data[0] = middle;
        //setText
        for(i=0;i<10;i++){
          data[i].setText(Data[i]);
        }*/
        before2_middle=before_middle;
        before_middle = middle;
        middle = "Clear";
      }else{
        num = 0;
      }
      Decimal = "F";
      decimal_cnt = 0;
      enzan = x;
    }
  rireki1.setText(before_middle);
  rireki2.setText(before2_middle);

  }
  public static void main(String[] args){
    new Dentaku();
  }
}
