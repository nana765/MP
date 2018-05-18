import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Dentaku extends JFrame implements ActionListener{
  JLabel caption = new JLabel("Java 電卓", JLabel.CENTER);
  JTextField kekka = new JTextField("0"); // 計算結果を表示するテキストフィールド
  JTextField kekka_middle = new JTextField("0"); // 計算の途中式を表示するテキストフィールド
  String[] bTxt={"CE","C","←","÷","7","8","9","×","4","5","6","-","1","2","3","+","+/-","0",".","="}; // ボタンの追加はこの行
  String enzan = "=";
  double num, total;

  public Dentaku() { // 画面レイアウトの作成
    setTitle("簡易ー電卓");
    setSize(500,500);
    setLocation(100,100);//(縦,横)
    JPanel panel1 = new JPanel(new GridLayout(2,1)); // 2行 1列
    JPanel panel2 = new JPanel(new GridLayout(5,4,8,8)); // 5行 4列 間隔 8
    caption.setForeground(Color.magenta);
    caption.setFont(new Font(Font.SERIF, Font.BOLD, 28));
    panel1.add(caption);

    Font font = new Font(Font.MONOSPACED, Font.BOLD, 32);
    kekka.setEditable(false);
    kekka.setHorizontalAlignment(JTextField.RIGHT);
    kekka.setFont(font);
    kekka.setBackground(Color.white);
    panel1.add(kekka);

    for(int i = 0; i < 20; i++) {
      JButton b = new JButton(bTxt[i]);
      b.addActionListener(this);
      b.setFont(font);
      b.setBackground(Color.white);
      if( i % 4==3 || i==16 || i < 3 || i > 17 ) b.setForeground(Color.blue); //演算記号は青
      panel2.add(b);
    }

    add(panel1, BorderLayout.NORTH);
    add(panel2, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ×ボタンで終了する
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) { // ボタンが押されたときの処理
    String x = ((JButton)e.getSource()).getText(); // 押されたボタンのテキストを取得
    if( Character.isDigit(x.charAt(0)) ){ // 数字ボタン
      num = num * 10 + Double.parseDouble(x);
      kekka.setText(String.valueOf(num));
    }else if( x=="." ){ // 小数点ボタン

    }else if( x=="C" ){ // 全クリアボタン
      num = total = 0;
      kekka.setText("0");
      enzan = "=";
    }else if( x =="CE" ){ // 一つクリアボタン
      num = 0;
      kekka.setText("0");
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
      if( x=="=" ) num = total;
      else num = 0;
      enzan = x;

    }
  }
  public static void main(String[] args){
    new Dentaku();
  }
}
