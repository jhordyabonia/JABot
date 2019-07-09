package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.Bot;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author jhord_000
 */
public final class Admin extends javax.swing.JFrame {
    public String file="";
    private String list_bots[];
    private String list_COMMANDS[];
    private String list_file_system[];
    private String tmp_bots="Select Bot",tmp_system="Select file",tmp_commands="Select comman";
    private String styles;
    /**
     * Creates new form Admin
     */
    public Admin() { super("JABot Manager");make();}
    private void make()
    {
        initComponents();
        cargarListaArchivos();
       
        list_bots=tmp_bots.split("::");
        list_file_system=tmp_system.split("::");
        list_COMMANDS=tmp_commands.split("::");
                
        items.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {               
                        String file_tmp=(String)items.getSelectedItem();
                        if(file==null)file="";
                        else if(file.equals(file_tmp))return;
                        load_file(file_tmp);
                        file=file_tmp;
                        System.out.println(">"+items.getSelectedItem()); 
                    } 
            }
            );   
        
        disabled(bots_);
        load_files();
        
        //editor.setContentType("text/html");
        styles="";//" <style> .comment{color:orange;}.key{color:green;}.command{color:blue}.done{font-weigth:700;}.error{color:red;text-decoration: line-through;}</style>";
        //editor.setText(styles+"<span class='error'>[ SYTEM RUN ]</span><br><span class='command'>[ENTER/]</span>");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        run = new javax.swing.JButton();
        items = new javax.swing.JComboBox();
        commands_ = new javax.swing.JRadioButton();
        bots_ = new javax.swing.JRadioButton();
        system_ = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        editor = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        erros = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        sources_content = new javax.swing.JScrollPane();
        sources = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextPane();
        edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        run.setText("Run");
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runActionPerformed(evt);
            }
        });

        items.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        items.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsActionPerformed(evt);
            }
        });

        commands_.setText("COMMANDS");
        commands_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commands_ActionPerformed(evt);
            }
        });

        bots_.setText("Bots");
        bots_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bots_ActionPerformed(evt);
            }
        });

        system_.setText("System");
        system_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                system_ActionPerformed(evt);
            }
        });

        jLabel1.setText("Type files:");

        editor.setEditable(false);
        editor.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jScrollPane3.setViewportView(editor);

        erros.setEditable(false);
        erros.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jScrollPane4.setViewportView(erros);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sourcesLayout = new javax.swing.GroupLayout(sources);
        sources.setLayout(sourcesLayout);
        sourcesLayout.setHorizontalGroup(
            sourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        sourcesLayout.setVerticalGroup(
            sourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        sources_content.setViewportView(sources);

        jLabel2.setText("Descrition:");

        description.setEditable(false);
        description.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jScrollPane2.setViewportView(description);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(sources_content, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sources_content, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Source", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53)
                        .addComponent(system_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(commands_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bots_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(run, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(run)
                    .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commands_)
                    .addComponent(bots_)
                    .addComponent(system_)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemsActionPerformed
           // TODO add your handling code here:
    }//GEN-LAST:event_itemsActionPerformed

    private void runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runActionPerformed
      runAction();
    }//GEN-LAST:event_runActionPerformed

    private void disabled(JRadioButton j)
    {
        system_.setSelected(false);
        commands_.setSelected(false);
        bots_.setSelected(false);
        j.setSelected(true);
        items.removeAllItems();        
    }
   
    private void commands_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commands_ActionPerformed
        disabled(commands_);
        load_files();
    }//GEN-LAST:event_commands_ActionPerformed

    private void get_description(String in)
    {
        boolean r=true;
        description.setText("");
        for(String des:in.split("::DESCRIP"))
        {
            r=!r;
            if(!r)continue;
            int i=des.indexOf("\n");
            if(i<=0)
                 description.setText(
                         description.getText()+
                                 des.substring(i,des.length()-1)+"\n"); 
           
        }
    }
    private void get_struct()
    {
        String datas="",commands="";
        /*
        String t[]=editor.getText().replaceAll("\\<.*?>","").replaceAll(",", "\n").replaceAll("  "," ").split("::");
        for(int o=0;o<t.length-2;o+=2)
        {   
            int g=t[o].indexOf("\n"),q=t[o+1].indexOf("\n");
            commands+="::"+t[o].substring(0, g==-1?t[o].length():g); 
            datas+="::"+t[o+1].substring(0, q==-1?t[o+1].length():q); 
        }
        String COMMANDS[]=commands.split("::");
        String DATAS[]=datas.split("::");
        for(int k=1;k<COMMANDS.length;k++)
        {
            System.out.println("?"+COMMANDS[k]+" + "+DATAS[k]);
        }*/
        
    }
    
    private void bots_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bots_ActionPerformed
        disabled(bots_); 
        load_files();        
    }//GEN-LAST:event_bots_ActionPerformed

    private void system_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_system_ActionPerformed
        disabled(system_); 
        load_files();
        //setFileSystem();
    }//GEN-LAST:event_system_ActionPerformed
    
    private void create()
    {        
        String newFile,ext=".jbot",err="";
        if(system_.isSelected())
            ext="";
        else if(commands_.isSelected())
            ext=".do";
        do
        {
            newFile=JOptionPane.showInputDialog("Nombre. "+err);
            err=" (Solo debe tener caracteres alfanumericas)";
        }while(newFile.contains(".")||newFile.equals(""));
        file="[MAKE "+newFile+ext+"],[/MAKE]";
        runAction();
        load_file(newFile+ext);
    }
    private void runAction()
    {          
         this.setExtendedState(ICONIFIED);
         java.awt.EventQueue.invokeLater(
         new Runnable() 
         {
            @Override
            public void run() 
            {               
                String t="LOG: ",args[]={"-d",file};
                try {Bot.main(args);} 
                catch (AWTException ex){t="ERROR:\n<Robot command not exist.>\n ";}
                catch (IOException ex){t="ERROR:\n<JABot file not exist.>\n ";}
                catch (InterruptedException ex){ t="ERROR:\n<System: unknown.>\n ";}
                finally
                {
                    err(t+file+" >>"+Bot.getLog());
                    setExtendedState( Frame.NORMAL);
                }
            }
        });
    }
    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        if(edit.getText().contains("Edit"))
        {   
            editor.setEditable(true);
            description.setEditable(true);
            
            editor.setBackground(Color.WHITE);
            description.setBackground(Color.WHITE);
            edit.setText("Save");
        }
        else if(save_file())
        { zero();}
// TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    public void zero()
    {
        editor.setEditable(false); 
        description.setEditable(false);
        editor.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        description.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        edit.setText("Edit");

    }
    /**
     * @param args the command line  arguments
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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
    private void load_files()
    {
        if(bots_.isSelected())
        {
            edit.setEnabled(true);
            description.setEnabled(true);
            for(String i:list_bots)
                items.addItem(i);
        }else if(system_.isSelected())
        {            
            edit.setEnabled(false);            
            description.setEnabled(false);            
            edit.setEnabled(true);//DEBUG
            description.setEnabled(true);//DEBUG
            for(String i:list_file_system)
                items.addItem(i);
        }else if(commands_.isSelected())
        {
            edit.setEnabled(false);     
            description.setEnabled(false);
            edit.setEnabled(true);//DEBUG
            description.setEnabled(true);//DEBUG
            for(String i:list_COMMANDS)
                items.addItem(i);
        }
    }
    public void load_file(String file)
    {           
        if(file==null)return;
        zero();
        editor.setText("Cargando... "+file); 
        FileReader f= null;
        try {f=new FileReader(file);} 
        catch (FileNotFoundException ex) 
        {System.out.println(file+" no Found!");return;}
        BufferedReader b=new BufferedReader(f);
        String c=null,a="";
        do
        { 
            try{ c=b.readLine();}
            catch (IOException ex) {}

            if(c==null)continue;
            if(c.equals(""))continue;
            a+="\n"+laws(c);
        }while(c!=null);     
        if(a.contains("::DESCRIP"))
            editor.setText(styles+a.substring(0,a.indexOf("::DESCRIP"))); 
        else editor.setText(styles+a);    
                
        get_description(a);       
        find_soucrces(a);
        get_struct();
    }
    private String laws(String cm0)
    {
        String class_="error ",out="";
        if(cm0==null)return null;
        
        cm0=cm0.replaceAll("  "," ");
        
        for(String cm:cm0.split(","))
        {
            if(cm.startsWith("[")&&cm.endsWith("]"))
                class_="";
            if(cm.contains("SYSTEM")
               ||cm.contains("WAIT")
               ||cm.contains("MOUSE")
               ||cm.contains("NOW")
               ||cm.contains("MAKE")
               ||cm.contains("REPEAT"))
                class_+=" command";

            if(cm.startsWith("::"))
                class_=" comment";
           // out+="<span class='"+class_+"'>"+cm+"</span><br>\n";
            out+=cm+"\n";
        }return cm0;
        
    }
    public void err(String e)
    { erros.setText(erros.getText()+e+"\n");}
    private final String removeHTML(String in)
    {
        String out="";
        for(String in1:in.replaceAll("\n"," ").split("<"))
        {
            String out0="";
            do
                in1=in1.replace("  ", " ");
            while(in1.contains("  "));
            if(in1.contains("error "))
            {
                System.err.println("Comming block: "+in1);  
                out0="::"+in1+"\n";
            }else if(in1.contains(">"))
            {
                if(in1.lastIndexOf(">")<in1.length()-1)
                    out0=in1.substring(in1.lastIndexOf(">")+1)+"\n";
                else 
                    out0=in1.substring(in1.lastIndexOf(">"))+"\n";
            }else if(in1.startsWith("::"))
                out0=in1+"\n";
            else 
                System.err.println("Omitting block: "+in1); 
            
            if(!(out0.contains(">")||out0.contains("<")))
                out+=out0;
        }
       return out.replaceAll(", ::"," \n::");
    }
    
    private boolean save_file()
    {
        FileWriter f=null;
        try {f = new FileWriter(file);} catch (IOException ex) 
        {}//Terminal.log("Err: Command no made");}
        try 
        {
           f.write(editor.getText().trim());
           //f.write(removeHTML(editor.getText()).trim());
           f.write("\n::DESCRIP");
           f.write("\n"+description.getText().trim());
           f.write("\n::DESCRIP");
           f.close();
           this.load_file(file);
        } catch (IOException ex){return false;}
        //Terminal.log("Err: Writing commad no made");}
        return true;
    }
    private void cargarListaArchivos()
    {       
        File dirFuente = new File(".");
        items.addItem("Select Bot");
        if(dirFuente.isDirectory()) 
        {
           FileFilter filter= new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    // Filtro, los archivos deben tener extension .do
                    String nombreArchivo = pathname.getName();
                   if(nombreArchivo.endsWith(".do"))
                        { tmp_commands+="::"+nombreArchivo; return true;}
                    else if(nombreArchivo.endsWith(".jbot"))
                       {tmp_bots+="::"+nombreArchivo; return true;}
                    else if(!nombreArchivo.contains(".")||file.endsWith(".dic"))
                        { tmp_system+="::"+nombreArchivo; return true;}       
                    
                    return false;                
                }
           };
           dirFuente.listFiles(filter);
     }
    }
    
    private void make_source(int t,final String S)
    {
        //if(S==null||t<0)return;
        JButton btn=new JButton("SET: "+S);
        btn.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {               
                        String args[]={"-r","[SOURCE OPEN],"+S+",[SOURCE /OPEN]"};
                        try {
                            Bot.main(args);
                        } catch (AWTException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        setVisible(true);
                        System.out.println(">"+args[1]); 
                    } 
                }
            );
        System.out.println(S);
        JPanel p=new JPanel();
        p.add(btn);
        sources.add(p,t,0);
        Dimension d=getSize();
        setSize(0,0);
        setSize(d);
    }
    
    private void find_soucrces(String d)
    {       
        String dd[]=d.split("\n");
        
        sources.removeAll();
        sources.setLayout(new GridLayout(1,dd.length));
        for(int y=0;y<dd.length;y++)
            if(dd[y].contains("[SOURCE OPEN]"))
                make_source(y,dd[++y]);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bots_;
    private javax.swing.JRadioButton commands_;
    private javax.swing.JTextPane description;
    private javax.swing.JButton edit;
    private javax.swing.JTextPane editor;
    private javax.swing.JTextPane erros;
    private javax.swing.JComboBox items;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton run;
    private javax.swing.JPanel sources;
    private javax.swing.JScrollPane sources_content;
    private javax.swing.JRadioButton system_;
    // End of variables declaration//GEN-END:variables
}
