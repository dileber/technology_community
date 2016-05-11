package com.zhonghua.dileber.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.zhonghua.dileber.app.SApplication;
import com.zhonghua.dileber.tools.SLog;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shidawei on 16/2/8.
 */
public class DBManager {

    private DBHelper dbHelper;
    public static volatile DBManager instance ;
    private SQLiteDatabase sqliteDatabase;

    private Map<String, DBHelper> dbHelperMap;

    /**
     * 构造函数
     * @param context   上下文对象
     */
    private DBManager(Context context) {
        //dbHelper = new DBHelper(context);
        //sqliteDatabase = dbHelper.getReadableDatabase();
        dbHelperMap = new HashMap<String, DBHelper>();
    }

    public DBManager getDB(String databaseName,Integer version,List<String> createSql){
        if (!dbHelperMap.containsKey(databaseName)) {
            dbHelper = new DBHelper(SApplication.getAppContext(),databaseName,null,version,createSql);
            dbHelperMap.put(databaseName, dbHelper);
        }
        dbHelper = dbHelperMap.get(databaseName);
        sqliteDatabase = dbHelper.getReadableDatabase();
        return this;
        //return dbHelperMap.get(databaseName);
    }



    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                instance = new DBManager(SApplication.getAppContext());
            }
        }
        return instance;
    }

    /**
     * 关闭数据库
     */
    public void close() {
        if(sqliteDatabase.isOpen()) sqliteDatabase.close();
        if(dbHelper != null) dbHelper.close();
        if(instance != null) instance = null;
    }

    /**
     * 插入数据
     * @param sql       执行更新操作的sql语句
     * @param bindArgs      sql语句中的参数,参数的顺序对应占位符顺序
     * @return  result      返回新添记录的行号，与主键id无关
     */
    public Long insertDataBySql(String sql, Object[] bindArgs) throws Exception{
        long result = 0;
        if(sqliteDatabase.isOpen()){
            SQLiteStatement statement = sqliteDatabase.compileStatement(sql);
            if(bindArgs != null){
                int size = bindArgs.length;
                for(int i = 0; i < size; i++){
                    //将参数和占位符绑定，对应
                    //statement.bindString(i+1, bindArgs[i]);
                    bind(bindArgs[i], statement, i + 1);
                }
                result = statement.executeInsert();
                statement.close();
            }
        }else{
            SLog.i("数据库已关闭");
        }
        return result;
    }

//    /**
//     * 插入数据
//     * @param table         表名
//     * @param values        要插入的数据
//     * @return  result      返回新添记录的行号，与主键id无关
//     */
//    public Long insertData(String table, ContentValues values){
//        long result = 0;
//        if(sqliteDatabase.isOpen()){
//            result = sqliteDatabase.Insert(table, null, values);
//        }
//        return result;
//    }

    /**
     * 更新数据
     * @param sql       执行更新操作的sql语句
     * @param bindArgs  sql语句中的参数,参数的顺序对应占位符顺序
     */
    public int updateDataBySql(String sql, Object[] bindArgs) throws Exception{
        if(sqliteDatabase.isOpen()){
            int res = 0;
            SQLiteStatement statement = sqliteDatabase.compileStatement(sql);
            if(bindArgs != null){
                int size = bindArgs.length;
                for(int i = 0; i < size; i++){
                    //statement.bindString(i+1, bindArgs[i]);
                    bind(bindArgs[i],statement,i + 1);
                }
                res = statement.executeUpdateDelete();
                statement.close();
            }
            return res;
        }else{
            SLog.i("数据库已关闭");
            return 0;
        }
    }

//    /**
//     * 更新数据
//     * @param table         表名
//     * @param values        表示更新的数据
//     * @param whereClause   表示SQL语句中条件部分的语句
//     * @param whereArgs     表示占位符的值
//     * @return
//     */
//    public int updataData(String table, ContentValues values, String whereClause, String[] whereArgs){
//        int result = 0;
//        if(sqliteDatabase.isOpen()){
//            result = sqliteDatabase.update(table, values, whereClause, whereArgs);
//        }
//        return result;
//    }

    /**
     * 删除数据
     * @param sql       执行更新操作的sql语句
     * @param bindArgs  sql语句中的参数,参数的顺序对应占位符顺序
     */
    public int deleteDataBySql(String sql, Object[] bindArgs) throws Exception{
        if(sqliteDatabase.isOpen()){
            SQLiteStatement statement = sqliteDatabase.compileStatement(sql);
            int res = 0;
            if(bindArgs != null){

                int size = bindArgs.length;
                for(int i = 0; i < size; i++){
                    //statement.bindString(i+1, bindArgs[i]);
                    bind(bindArgs[i],statement,i + 1);
                }
//                Method[] mm = statement.getClass().getDeclaredMethods();
//                for (Method method : mm) {
//                    SLog.i( method.getName());
//                    /**
//                     *  反射查看是否能获取executeUpdateDelete方法
//                     *  查看源码可知 executeUpdateDelete是public的方法，但是好像被隐藏了所以不能被调用，
//                     *      利用反射貌似只能在root以后的机器上才能调用，小米是可以，其他机器却不行，所以还是不能用。
//                     */
//                }
                res = statement.executeUpdateDelete();
                statement.close();
            }
            return res;
        }else{
            SLog.i( "数据库已关闭");
            return 0;
        }
    }

//    /**
//     * 删除数据
//     * @param table         表名
//     * @param whereClause   表示SQL语句中条件部分的语句
//     * @param whereArgs     表示占位符的值
//     * @return
//     */
//    public int deleteData(String table, String whereClause, String[] whereArgs){
//        int result = 0;
//        if(sqliteDatabase.isOpen()){
//            result = sqliteDatabase.delete(table, whereClause, whereArgs);
//        }
//        return result;
//    }

    /**
     * 查询数据
     * @param sql         执行查询操作的sql语句
     * @param selectionArgs     查询条件
     * @return                  返回查询的游标，可对数据自行操作，需要自己关闭游标
     */
    public Cursor queryData2Cursor(String sql, String[] selectionArgs) throws Exception{
        if(sqliteDatabase.isOpen()){
            Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }
        return null;
    }

    /**
     * 查询数据
     * @param sql               执行查询操作的sql语句
     * @param selectionArgs     查询条件
     * @param object                Object的对象
     * @return List<Object>       返回查询结果
     */
    public List<Object> queryData2Object(String sql, String[] selectionArgs, Object object) throws Exception{
        List<Object> mList = new ArrayList<Object>();
        if(sqliteDatabase.isOpen()){
            Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
            Field[] f;
            if(cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    f = object.getClass().getDeclaredFields();
                    for(int i = 0; i < f.length; i++) {
                        //为JavaBean 设值
                        invokeSet(object, f[i].getName(), cursor.getString(cursor.getColumnIndex(f[i].getName())),f[i].getType());
                    }
                    mList.add(object);
                }
            }
            cursor.close();
        }else{
            SLog.i( "数据库已关闭");
        }
        return mList;
    }

    public <T> List<T> queryData2T(String sql, Object[] selectionArgs, Class<T> clazz) throws Exception{
        List<T> mList = new ArrayList<T>();
        if(sqliteDatabase.isOpen()){
            String[] selection = null;
            if(selectionArgs!=null){
                selection = new String[selectionArgs.length];
                for(int i=0;i<selectionArgs.length;i++){
                    Object o = selectionArgs[i];
                    String mz = String.valueOf(o);
                    selection[i] = mz;
                }
            }


            Cursor cursor = sqliteDatabase.rawQuery(sql, selection);
            Field[] f;
            if(cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    T t = clazz.newInstance();
                    f = clazz.getDeclaredFields();
                    for(int i = 0; i < f.length; i++) {
                        //为JavaBean 设值
                        //invokeSet(object, f[i].getName(), cursor.getString(cursor.getColumnIndex(f[i].getName())));
                        invokeSet(t,f[i].getName(), cursor.getString(cursor.getColumnIndex(f[i].getName())),f[i].getType());
                    }
                    mList.add(t);
                }
            }
            cursor.close();
        }else{
            SLog.i( "数据库已关闭");
        }
        return mList;
    }





    /**
     * 查询数据
     * @param sql                           执行查询操作的sql语句
     * @param selectionArgs                 查询条件
     * @param object                            Object的对象
     * @return  List<Map<String, Object>>   返回查询结果
     * @throws Exception
     */
    public List<Map<String, Object>> queryData2Map(String sql, String[] selectionArgs, Object object)throws Exception{
        List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
        if(sqliteDatabase.isOpen()){


            Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
            Field[] f;
            Map<String, Object> map;
            if(cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    map = new HashMap<String, Object>();
                    f = object.getClass().getDeclaredFields();
                    for(int i = 0; i < f.length; i++) {
                        map.put(f[i].getName(), cursor.getString(cursor.getColumnIndex(f[i].getName())));
                    }
                    mList.add(map);
                }
            }
            cursor.close();
        }else{
            SLog.i("数据库已关闭");
        }
        return mList;
    }


    public List<Map<String, Object>> query2Map(String sql, Object[] selectionArgs)throws Exception{
        List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
        if(sqliteDatabase.isOpen()){

            String[] selection = null;
            if(selectionArgs!=null){
                selection = new String[selectionArgs.length];
                for(int i=0;i<selectionArgs.length;i++){
                    Object o = selectionArgs[i];
                    String mz = String.valueOf(o);
                    selection[i] = mz;
                }
            }

            Cursor cursor = sqliteDatabase.rawQuery(sql, selection);
            Field[] f;
            Map<String, Object> map;
            if(cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    map = new HashMap<String, Object>();
                    for(int i = 0; i < cursor.getColumnCount(); i++) {
                        String name = cursor.getColumnName(i);
                        map.put(name, cursor.getString(cursor.getColumnIndex(name)));
                    }
                    mList.add(map);
                }
            }
            cursor.close();
        }else{
            SLog.i("数据库已关闭");
        }
        return mList;
    }

    /**
     * 事物处理
     *
     * @param t
     */
    public void transaction(TransT t)
    {
        // 采用事务处理，确保数据完整性
        sqliteDatabase.beginTransaction(); // 开始事务
        try
        {
            t.transcation(sqliteDatabase);
            sqliteDatabase.setTransactionSuccessful(); // 设置事务成功完成
        }
        finally
        {
            sqliteDatabase.endTransaction(); // 结束事务
        }
    }

    public interface TransT{
        void transcation(SQLiteDatabase sqliteDatabase);
    }


    public interface Type{
        int UPDATE = 0;
        int INSERT = 1;
        int DELETE = 3;
    }


    public int dataBatch(String sql, List<Object[]> lists,int type)
    {
        int count = 0;

        if(sqliteDatabase.isOpen()){

        }else{
            SLog.i("数据库已关闭");
        }
        // 采用事务处理，确保数据完整性
        sqliteDatabase.beginTransaction(); // 开始事务
        try
        {

            SQLiteStatement statement = sqliteDatabase.compileStatement(sql);


            for(Object[] bindArgs:lists){
                if(bindArgs != null){
                    int size = bindArgs.length;
                    for(int i = 0; i < size; i++){
                        //将参数和占位符绑定，对应
                        //statement.bindString(i + 1, bindArgs[i]);
                        bind(bindArgs[i],statement,i + 1);
                    }
                    if(type==Type.INSERT){
                        statement.executeInsert();
                    }else{
                        statement.execute();
                    }
                    count++;
                }

            }
            statement.close();
            sqliteDatabase.setTransactionSuccessful(); // 设置事务成功完成
        }
        finally
        {
            sqliteDatabase.endTransaction(); // 结束事务
        }
        return count;
    }

    private void bind(Object object,SQLiteStatement statement,int pos){
        if(object==null){
            statement.bindNull(pos);
        }else if (object instanceof String)
        {
            statement.bindString(pos, (String) object);
        } else if (object instanceof Long)
        {
            statement.bindLong(pos, (Long) object);
        } else if (object instanceof byte[])
        {
            statement.bindBlob(pos, (byte[]) object);
        } else if (object instanceof Double)
        {
            statement.bindDouble(pos, (Double) object);
        } else if (object instanceof Integer)
        {
            statement.bindLong(pos, (Integer)object);
        }else {
            statement.bindString(pos,object.toString());
        }
    }



    /**
     * java反射bean的set方法
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行set方法
     * @param object    执行对象
     * @param fieldName 属性
     * @param value     值
     */
    public static <T>void  invokeSet(T t, String fieldName, String value,Class<? extends Object> typeClass) {

        Method method = getSetMethod(t.getClass(), fieldName);
        try {
            Constructor<? extends Object> cons = typeClass.getConstructor(String.class);
            Object attribute = cons.newInstance(value);
            method.invoke(t, new Object[] { attribute });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T>T invokeSet(Class<T> clazz,String fieldName,Object value){
        Method method = getSetMethod(clazz, fieldName);
        T t = null;
        try {
            t = clazz.newInstance();
            method.invoke(t, new Object[] { value });
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }


    public <T>T loadMapper(Class<T> clazz){


//        Class<?> classType = handler.getClass() ;
//
//        // classType.getClassLoader() 动态代理类的类加载器
//        //rsd.getClass().getInterfaces() 代理类要实现的接口列表
//        //handler 指派方法调用的调用处理程序
//        SubjectDemo sd = (SubjectDemo)Proxy.newProxyInstance(classType.getClassLoader(), rsd.getClass().getInterfaces(), handler ) ;
//
//        //这行代码一执行 转到 InvocationHandler handler = new DynamicProxySubjectDemo(rsd)
//        //执行invoke方法
//        sd.request() ;

        final MapperProxy<T> mapperProxy = new MapperProxy<T>(clazz,new ConcurrentHashMap<Method, MapperMethod>(),this);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, mapperProxy);

//        clazz.getAnnotations();
//        final MapperFactory<T> mapperFactory = (MapperFactory<T>)
//
//        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
//        return mapperProxyFactory.newInstance(sqlSession);
    }

}