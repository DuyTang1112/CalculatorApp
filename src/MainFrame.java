
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Duy Anh Tang
 */
public class MainFrame extends javax.swing.JFrame {
    
    private double operand1 = -1;
    private double operand2 = -1;
    private String operand1str = new String("");
    private String operand2str = new String("");
    private String operation = new String("");
    private boolean isInOperand1 = true;
    private boolean isInOperand2 = false;
    private boolean resultReady = false;
    private boolean resultCreated = false;
    private boolean hasDot = false;
    private boolean hasOperation = false;
    

    private ActionListener digitHandler = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (resultCreated) {
                operand1str = new String("");
                resultCreated = false;
                hasDot = false;
            }
            if (ae.getActionCommand().equals("π") || ae.getActionCommand().equals("e")) {
                if (isInOperand1 && operand1str.equals("")) {
                    operand1str = ae.getActionCommand();
                    operand1 = ae.getActionCommand().equals("π") ? Math.PI : Math.E;
                    isInOperand1 = false;
                    resultReady = true;
                    hasDot=true;
                    displayArea.setText(operand1str);
                } else if (isInOperand2 && operand2str.equals("")) {
                    operand2str = ae.getActionCommand();
                    operand2 = ae.getActionCommand().equals("π") ? Math.PI : Math.E;;
                    isInOperand2 = false;
                    resultReady = true;
                    hasDot=true;
                    displayArea.setText(operand1str + operation + operand2str);
                }
            } else if (isInOperand1) {
                
                if (operand1 == 0 && ae.getActionCommand().equals("0")&&!hasDot) {
                    operand1str="";
                    return;
                }

                operand1str += ae.getActionCommand();
                operand1 = Double.parseDouble(operand1str);
                displayArea.setText(operand1str);
                resultReady = false;
            } else if (isInOperand2) {
                if (operand2 == 0 && ae.getActionCommand().equals("0")&&!hasDot) {
                    operand2str="";
                    return;
                }
                operand2str += ae.getActionCommand();
                operand2 = Double.parseDouble(operand2str);
                displayArea.setText(operand1str + operation + operand2str);
                resultReady = true;
            }

        }
    };
    private ActionListener operationHandler = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (hasOperation) {
                if (operand2str.equals("")) {
                    operation = ae.getActionCommand();
                    displayArea.setText(operand1str + operation);
                } //alow to change the operation if there's no operand2
                return;
            } 
            
            else {
//                try {
//                    System.out.println(operand1str);
//                    double temp=Double.parseDouble(operand1str);
//                    System.out.println(temp);
//                } catch (Exception e){
//                    return;
//                }// Double.parseDouble("2.")==2.0
                if (operand1str.length()>0&&operand1str.charAt(operand1str.length()-1)=='.'){
                    operand1str=operand1str.substring(0, operand1str.length()-1);
                }
                resultCreated = false;
                hasOperation = true;
                hasDot = false;
                operation = ae.getActionCommand();
                isInOperand1 = false;
                isInOperand2 = true;
                resultReady = false;
                displayArea.setText(operand1str + operation);
            }
        }
    };
    private ActionListener functionHandler = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!hasOperation && !operand1str.equals("")&&operand1str.charAt(operand1str.length()-1)!='.') {
                switch (ae.getActionCommand()) {
                    case "1/N":
                        operand1 = (double) Math.round(1 / operand1 * 1000000000) / 1000000000;
                        break;
                    case "sin":
                        operand1 = (double) Math.round(Math.sin(operand1) * 1000000000) / 1000000000;
                        break;
                    case "cos":
                        operand1 = (double) Math.round(Math.cos(operand1) * 1000000000) / 1000000000;
                        break;
                    case "tan":
                        operand1 = (double) Math.round(Math.tan(operand1) * 1000000000) / 1000000000;
                        break;
                    case "sqrt":
                        if (operand1 < 0) {
                            displayArea.setText("Error");
                            return;
                        }
                        operand1 = (double) Math.round(Math.sqrt(operand1) * 1000000000) / 1000000000;
                        break;
                    case "square":
                        operand1 = (double) Math.round(Math.pow(operand1, 2) * 1000000000) / 1000000000;
                        break;
                    case "cube":
                        operand1 = (double) Math.round(Math.pow(operand1, 3) * 1000000000) / 1000000000;
                        break;
                    case "cuberoot":
                        operand1 = (double) Math.round(Math.cbrt(operand1) * 1000000000) / 1000000000;
                        break;
                    case "log":
                        if (operand1 <= 0) {
                            displayArea.setText("Error");
                            return;
                        }
                        operand1 = (double) Math.round(Math.log10(operand1) * 1000000000) / 1000000000;
                        break;
                    case "ln":
                        if (operand1 <= 0) {
                            displayArea.setText("Error");
                            return;
                        }
                        operand1 = (double) Math.round(Math.log(operand1) * 1000000000) / 1000000000;
                        break;
                    case "asin":
                        operand1 = (double) Math.round(Math.asin(operand1) * 1000000000) / 1000000000;
                        break;
                    case "acos":
                        operand1 = (double) Math.round(Math.acos(operand1) * 1000000000) / 1000000000;
                        break;
                    case "atan":
                        operand1 = (double) Math.round(Math.atan(operand1) * 1000000000) / 1000000000;
                        break;
                }
                
                isInOperand1 = true;
                resultCreated = true;
                resultReady = false;
                if (operand1 - Math.round(operand1) == 0) {
                    operand1str = String.valueOf((long) operand1);
                    System.out.println("hasdot false "+operand1str);
                    hasDot = false;
                } else {
                    operand1str = String.valueOf(operand1);
                    hasDot = true;
                }
                displayArea.setText(operand1str);

            } else if (resultReady && !operand2str.equals("")) {
                switch (ae.getActionCommand()) {
                    case "1/N":
                        operand2 = (double) Math.round(1 / operand2 * 1000000000) / 1000000000;
                        break;
                    case "sin":
                        operand2 = (double) Math.round(Math.sin(operand2) * 1000000000) / 1000000000;
                        break;
                    case "cos":
                        operand2 = (double) Math.round(Math.cos(operand2) * 1000000000) / 1000000000;
                        break;
                    case "tan":
                        operand2 = (double) Math.round(Math.tan(operand2) * 1000000000) / 1000000000;
                        break;
                    case "sqrt":
                        if (operand2 < 0) {
                            displayArea.setText("Error");
                            return;
                        }
                        operand2 = (double) Math.round(Math.sqrt(operand2) * 1000000000) / 1000000000;
                        break;
                    case "square":
                        operand2 = (double) Math.round(Math.pow(operand2, 2) * 1000000000) / 1000000000;
                        break;
                    case "cube":
                        operand2 = (double) Math.round(Math.pow(operand2, 3) * 1000000000) / 1000000000;
                        break;
                    case "cuberoot":
                        operand2 = (double) Math.round(Math.cbrt(operand2) * 1000000000) / 1000000000;
                        break;
                    case "log":
                        if (operand2 <= 0) {
                            displayArea.setText("Error");
                            return;
                        }
                        operand2 = (double) Math.round(Math.log10(operand2) * 1000000000) / 1000000000;
                        break;
                    case "ln":
                        if (operand2 <= 0) {
                            displayArea.setText("Error");
                            return;
                        }
                        operand2 = (double) Math.round(Math.log(operand2) * 1000000000) / 1000000000;
                        break;
                    case "asin":
                        operand2 = (double) Math.round(Math.asin(operand2) * 1000000000) / 1000000000;
                        break;
                    case "acos":
                        operand2 = (double) Math.round(Math.acos(operand2) * 1000000000) / 1000000000;
                        break;
                    case "atan":
                        operand2 = (double) Math.round(Math.atan(operand2) * 1000000000) / 1000000000;
                        break;
                }

                isInOperand2 = false;
                resultReady = true;
                resultCreated = false;
                if (operand2 - Math.round(operand2) == 0) {
                    operand2str = String.valueOf((long) operand2);
                    hasDot = false;
                } else {
                    operand2str = String.valueOf(operand2);
                    hasDot = true;
                }
                if (operand2 < 0) {
                    operand2str = "(" + operand2str + ")";
                }
                displayArea.setText(operand1str + operation + operand2str);
            }
        }
    };
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();// setting components
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Simple Scientific Calculator");
        button0.addActionListener(digitHandler);
        button1.addActionListener(digitHandler);
        button2.addActionListener(digitHandler);
        button3.addActionListener(digitHandler);
        button4.addActionListener(digitHandler);
        button5.addActionListener(digitHandler);
        button6.addActionListener(digitHandler);
        button7.addActionListener(digitHandler);
        button8.addActionListener(digitHandler);
        button9.addActionListener(digitHandler);
        pi.addActionListener(digitHandler);
        e.addActionListener(digitHandler);
        plus.addActionListener(operationHandler);
        minus.addActionListener(operationHandler);
        multiply.addActionListener(operationHandler);
        divide.addActionListener(operationHandler);
        modulus.addActionListener(operationHandler);
        dotButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (resultCreated) {
                    resultCreated = false;
                    operand1str = ".";
                    hasDot=true;
                    resultReady = false;
                    displayArea.setText(operand1str);
                    isInOperand1 = true;
                }
                if (hasDot) {
                    return;
                }
                if (!hasOperation) {
                    operand1str += ".";
                    resultReady = false;
                    hasDot = true;
                    displayArea.setText(operand1str);
                    System.out.println("!!");
                } else if (isInOperand2) {
                    operand2str += ".";
                    resultReady = false;
                    hasDot = true;
                    displayArea.setText(operand1str + operation + operand2str);
                }
            }
        });
        equalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (resultReady) {

                    if ((operand1str.equals("π") || operand1str.equals("e")) && !hasOperation) {
                        displayArea.setText(operand1 + "");
                        resultCreated = true;
                        hasDot = true;
                        isInOperand1 = true;
                        return;
                    }
                    switch (operation) {
                        case "+":
                            operand1 = operand1 + operand2;
                            break;
                        case "-":
                            operand1 = operand1 - operand2;
                            break;
                        case "*":
                            operand1 = operand1 * operand2;
                            break;
                        case "/":
                            if (operand2 == 0) {
                                displayArea.setText("Error");
                                operand2str="";
                                return;
                            }
                            operand1 = operand1 / operand2;
                            break;
                        case "%":
                            operand1 = operand1 % operand2;
                            break;
                    }
                    operand1 = (double) Math.round(operand1 * 1000000000) / 1000000000;
                    resultCreated = isInOperand1 = true;
                    resultReady = hasOperation = isInOperand2 = false;
                    operation = new String("");
                    operand2str = new String("");
                    operand2 = -1; // handle if you input too many 0 in operand 2
                    if (operand1 - Math.round(operand1) == 0) {
                        displayArea.setText((long) operand1 + "");
                        operand1str = String.valueOf((long) operand1);
                        hasDot = false;
                    } else {
                        displayArea.setText(operand1 + "");
                        operand1str = String.valueOf(operand1);
                        hasDot = true;
                    }
                }
            }
        });
        inverse.addActionListener(functionHandler);
        sin.addActionListener(functionHandler);
        cos.addActionListener(functionHandler);
        tan.addActionListener(functionHandler);
        sqroot.addActionListener(functionHandler);
        square.addActionListener(functionHandler);
        cube.addActionListener(functionHandler);
        cubert.addActionListener(functionHandler);
        log.addActionListener(functionHandler);
        ln.addActionListener(functionHandler);
        plusminus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!hasOperation) {
                    if (operand1 == 0) {
                        return;
                    }
                    operand1 = -operand1;
                    if (operand1str.charAt(0) == '-') {
                        operand1str = operand1str.substring(1);
                    } else {
                        operand1str = "-" + operand1str;
                    }
                    displayArea.setText(operand1str);
                } else if (resultReady) {
                    if (operand2 == 0) {
                        return;
                    }
                    operand2 = -operand2;
                    if (operand2str.charAt(0) == '-') {
                        operand2str = operand2str.substring(1);
                    } else {
                        operand2str = "-" + operand2str + "";
                    }
                    displayArea.setText(operand1str + operation + operand2str);
                }
            }
        });
        asin.addActionListener(functionHandler);
        acos.addActionListener(functionHandler);
        atan.addActionListener(functionHandler);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        displayArea = new javax.swing.JTextField();
        button1 = new javax.swing.JButton();
        button2 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        button4 = new javax.swing.JButton();
        button5 = new javax.swing.JButton();
        button6 = new javax.swing.JButton();
        button7 = new javax.swing.JButton();
        button8 = new javax.swing.JButton();
        button9 = new javax.swing.JButton();
        button0 = new javax.swing.JButton();
        dotButton = new javax.swing.JButton();
        equalButton = new javax.swing.JButton();
        plus = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        multiply = new javax.swing.JButton();
        divide = new javax.swing.JButton();
        modulus = new javax.swing.JButton();
        pi = new javax.swing.JButton();
        e = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        inverse = new javax.swing.JButton();
        sin = new javax.swing.JButton();
        cos = new javax.swing.JButton();
        tan = new javax.swing.JButton();
        sqroot = new javax.swing.JButton();
        square = new javax.swing.JButton();
        plusminus = new javax.swing.JButton();
        cube = new javax.swing.JButton();
        cubert = new javax.swing.JButton();
        log = new javax.swing.JButton();
        ln = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        asin = new javax.swing.JButton();
        acos = new javax.swing.JButton();
        atan = new javax.swing.JButton();

        jButton6.setText("jButton6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        displayArea.setEditable(false);
        displayArea.setBackground(new java.awt.Color(228, 228, 229));
        displayArea.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        displayArea.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayArea.setText("0");
        displayArea.setHighlighter(null);

        button1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button1.setText("1");

        button2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button2.setText("2");

        button3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button3.setText("3");

        button4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button4.setText("4");

        button5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button5.setText("5");

        button6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button6.setText("6");

        button7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button7.setText("7");

        button8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button8.setText("8");

        button9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button9.setText("9");

        button0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button0.setText("0");

        dotButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dotButton.setText(".");

        equalButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        equalButton.setText("=");

        plus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        plus.setText("+");

        minus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        minus.setText("-");

        multiply.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        multiply.setText("*");

        divide.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        divide.setText("/");

        modulus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        modulus.setText("%");

        pi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pi.setText("π");

        e.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        e.setText("e");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("C");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        inverse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inverse.setText("1/N");

        sin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sin.setText("sin");

        cos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cos.setText("cos");

        tan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tan.setText("tan");

        sqroot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sqroot.setText("√");
        sqroot.setActionCommand("sqrt");

        square.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        square.setText("x^2");
        square.setActionCommand("square");

        plusminus.setText("+/-");

        cube.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cube.setText("x^3");
        cube.setActionCommand("cube");

        cubert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cubert.setText("3√");
        cubert.setActionCommand("cuberoot");

        log.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        log.setText("log");

        ln.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ln.setText("ln");

        asin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        asin.setText("arcsin");
        asin.setActionCommand("asin");

        acos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acos.setText("arccos");
        acos.setActionCommand("acos");

        atan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        atan.setText("arctan");
        atan.setActionCommand("atan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(asin, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(atan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asin)
                    .addComponent(acos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atan)
                .addGap(0, 55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(button0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(divide, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modulus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(plusminus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(multiply, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cubert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sqroot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(log, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(square, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cube, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inverse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ln, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(displayArea, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(displayArea, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pi)
                    .addComponent(e)
                    .addComponent(inverse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sin)
                    .addComponent(cos)
                    .addComponent(tan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sqroot)
                    .addComponent(square)
                    .addComponent(cube))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dotButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cubert)
                    .addComponent(log)
                    .addComponent(ln))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(multiply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(plus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(divide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modulus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plusminus)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        displayArea.setText("0");
        isInOperand1 = true;
        isInOperand2 = false;
        hasDot = false;
        hasOperation = false;
        resultReady = true;
        operand1str = operand2str = "";
        operand1 = 0;
        operand2=-1;
        resultCreated=false;
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acos;
    private javax.swing.JButton asin;
    private javax.swing.JButton atan;
    private javax.swing.JButton button0;
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JButton button3;
    private javax.swing.JButton button4;
    private javax.swing.JButton button5;
    private javax.swing.JButton button6;
    private javax.swing.JButton button7;
    private javax.swing.JButton button8;
    private javax.swing.JButton button9;
    private javax.swing.JButton cos;
    private javax.swing.JButton cube;
    private javax.swing.JButton cubert;
    private javax.swing.JTextField displayArea;
    private javax.swing.JButton divide;
    private javax.swing.JButton dotButton;
    private javax.swing.JButton e;
    private javax.swing.JButton equalButton;
    private javax.swing.JButton inverse;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton ln;
    private javax.swing.JButton log;
    private javax.swing.JButton minus;
    private javax.swing.JButton modulus;
    private javax.swing.JButton multiply;
    private javax.swing.JButton pi;
    private javax.swing.JButton plus;
    private javax.swing.JButton plusminus;
    private javax.swing.JButton sin;
    private javax.swing.JButton sqroot;
    private javax.swing.JButton square;
    private javax.swing.JButton tan;
    // End of variables declaration//GEN-END:variables
}
