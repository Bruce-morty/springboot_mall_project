package top.philxin.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: String2StringArrayTypeHandler
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 23:50
 * @version: v1.0
 */
@MappedTypes(int[].class)
public class String2IntArrayTypeHandler implements TypeHandler<int[]> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, int[] ints, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(ints);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(i,s);
    }

    @Override
    public int[] getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return transfer(string);
    }

    @Override
    public int[] getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return transfer(string);
    }

    @Override
    public int[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return transfer(string);
    }


    private int[] transfer(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        int[] ints = new int[0];
        try {
            ints = objectMapper.readValue(result, int[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ints;
    }
    /*@Override
    public void setParameter(PreparedStatement preparedStatement, int index, String[] strings, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String string = null;
        try {
            string = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index,string);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String result = resultSet.getString(s);
        return transfer(result);
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String result = resultSet.getString(i);
        return transfer(result);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String result = callableStatement.getString(i);
        return transfer(result);
    }

    private String[] transfer(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[0];
        try {
            strings = objectMapper.readValue(result, String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }*/
}
