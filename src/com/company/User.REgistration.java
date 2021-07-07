String image_path = null;

public Register_Form() {
        initComponents();

        // center the form
        this.setLocationRelativeTo(null);

        // create a yellow border for the jpanel_title
        // 0 border in the top
        Border jpanel_title_border = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.yellow);
        // set the border to the jPanel_title
        jPanel_title.setBorder(jpanel_title_border);

        // create a black border for the close & minimize jlables
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        jLabel_minimize.setBorder(label_border);
        jLabel_close.setBorder(label_border);

        // create a border for the create acconut jlabel
        Border label_login_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        jLabel_login.setBorder(label_login_border);


        // create border for the text and password fields
        Border field_border = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.white);
        jTextField_Username.setBorder(field_border);
        jTextField_Fullname.setBorder(field_border);
        jTextField_Phone.setBorder(field_border);
        jPasswordField_1.setBorder(field_border);
        jPasswordField_2.setBorder(field_border);

        // create a button group for the radiobuttons
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButton_Male);
        bg.add(jRadioButton_Female);
        }


// jlabel minimize -> mouse click event
private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {
        this.setState(JFrame.ICONIFIED);

        }

// jlabel  minimize -> mouse entered event
private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {
        // change the jlabel border color to white
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabel_minimize.setBorder(label_border);
        // set the jlabel foreground to white
        jLabel_minimize.setForeground(Color.white);
        }

// jlabel minimize -> mouse exited event
private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {
        // reset the jlabel border color to white
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        jLabel_minimize.setBorder(label_border);
        // reset the jlabel foreground to white
        jLabel_minimize.setForeground(Color.black);

        }

// jlabel close -> mouse click event
private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
        }


// jlabel close -> mouse entered event
private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {
        // change the jlabel border color to white
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabel_close.setBorder(label_border);
        // set the jlabel foreground to white
        jLabel_close.setForeground(Color.white);

        }

// jlabel close -> mouse exited event
private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {
        // reset the jlabel border color to white
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        jLabel_close.setBorder(label_border);
        // reset the jlabel foreground to black
        jLabel_close.setForeground(Color.black);

        }

// JTextField For The Phone Number -> KeyTyped Event
private void jTextField_PhoneKeyTyped(java.awt.event.KeyEvent evt) {
        // allow only numbers
        if(!Character.isDigit(evt.getKeyChar())){
        evt.consume();
        }
        }


// button select image
private void jButton_SelectImageActionPerformed(java.awt.event.ActionEvent evt) {
        // select an image and set the image path into a jlabel
        String path = null;

        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*.Images","jpg","png","jpeg");
        chooser.addChoosableFileFilter(extension);

        int filestate = chooser.showSaveDialog(null);

        // check if the user select an image
        if(filestate == JFileChooser.APPROVE_OPTION){

        File selectedImage = chooser.getSelectedFile();
        path = selectedImage.getAbsolutePath();
        jLabel_imgpath.setText(path);

        image_path = path;
        }

        }

// a jlabel to open the login form on Mouse Clicked Event
private void jLabel_loginMouseClicked(java.awt.event.MouseEvent evt) {
        Login_Form lf = new Login_Form();
        lf.setVisible(true);
        lf.pack();
        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();

        }

// change the jlabel border color on mouse entered
private void jLabel_loginMouseEntered(java.awt.event.MouseEvent evt) {
        Border label_border = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,204,0));
        jLabel_login.setBorder(label_border);

        }

// reset the jlabel border color on mouse exited
private void jLabel_loginMouseExited(java.awt.event.MouseEvent evt) {
        Border label_create_accont_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray);
        jLabel_login.setBorder(label_create_accont_border);

        }

// create a function to verify the empty fields
public boolean verifyFields()
        {
        String fname = jTextField_Fullname.getText();
        String uname = jTextField_Username.getText();
        String phone = jTextField_Phone.getText();
        String pass1 = String.valueOf(jPasswordField_1.getPassword());
        String pass2 = String.valueOf(jPasswordField_2.getPassword());

        // check empty fields
        if(fname.trim().equals("") || uname.trim().equals("") || phone.trim().equals("")
        || pass1.trim().equals("") || pass2.trim().equals(""))
        {
        JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty","Empty Fields",2);
        return false;
        }

        // check if the two password are equals or not
        else if(!pass1.equals(pass2))
        {
        JOptionPane.showMessageDialog(null, "Password Doesn't Match","Confirm Password",2);
        return false;
        }

        // if everything is ok
        else{
        return true;
        }
        }


// create a function to check if the entered username already exists in the database
public boolean checkUsername(String username){

        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `users` WHERE `username` = ?";

        try {

        st = My_CNX.getConnection().prepareStatement(query);
        st.setString(1, username);
        rs = st.executeQuery();

        if(rs.next())
        {
        username_exist = true;
        JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
        }

        } catch (SQLException ex) {
        Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username_exist;
        }


// button register
private void jButton_RegisterActionPerformed(java.awt.event.ActionEvent evt) {
        // get data from fields
        String fname = jTextField_Fullname.getText();
        String username = jTextField_Username.getText();
        String pass1 = String.valueOf(jPasswordField_1.getPassword());
        String pass2 = String.valueOf(jPasswordField_2.getPassword());
        String phone = jTextField_Phone.getText();
        String gender = "Male";

        if(jRadioButton_Female.isSelected()){
        gender = "Female";
        }

// check if the data are empty
        if(verifyFields())
        {
// check if the username already exists
        if(!checkUsername(username))
        {
        PreparedStatement ps;
        ResultSet rs;
        String registerUserQuery = "INSERT INTO `users`(`full_name`, `username`, `password`, `phone`, `gender`, `picture`) VALUES (?,?,?,?,?,?)";

        try {

        ps = My_CNX.getConnection().prepareStatement(registerUserQuery);
        ps.setString(1, fname);
        ps.setString(2, username);
        ps.setString(3, pass1);
        ps.setString(4, phone);
        ps.setString(5, gender);

        try {

        // save the image as blob in the database
        if(image_path != null){

        InputStream image = new FileInputStream(new File(image_path));
        ps.setBlob(6, image);

        }else{
        ps.setNull(6, java.sql.Types.NULL);
        }

        if(ps.executeUpdate() != 0){
        JOptionPane.showMessageDialog(null, "Your Account Has Been Created");
        }else{
        JOptionPane.showMessageDialog(null, "Error: Check Your Information");
        }

        } catch (FileNotFoundException ex) {
        Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
        }

        } catch (SQLException ex) {
        Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
        }

        }          
