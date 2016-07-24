package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GeneratorDemo {

    public static void main(String[] args){
        Schema schema = new Schema(1,"com.example.administrator.southweek.ui.db");

         addTable(schema);

        try {
            DaoGenerator daoGenerator = new DaoGenerator();
            daoGenerator.generateAll(schema,
                    "C:\\Users\\Administrator\\Desktop\\11\\app\\src\\main\\java");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTable(Schema schema){
        Entity entity_user = schema.addEntity("User");
        entity_user.setTableName("tab_users");
        entity_user.addIntProperty("id").primaryKey().autoincrement();
        entity_user.addStringProperty("user_name") ; // 昵称
        entity_user.addIntProperty("user_psw"); // 密码
        entity_user.addBooleanProperty("user_gender"); // 性别
        entity_user.addStringProperty("user_hobby"); // 爱好
        entity_user.addStringProperty("user_sign"); //签名
        entity_user.addByteArrayProperty("user_portait"); //头像
    }

}
