package template.ws.data;

import org.testng.annotations.Test;
import template.data.PreparedStatementWrapper;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: taoluo
 * Date: 10/31/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Test(groups = {"unit_test"})
public class PreparedStatementWrapperTest {

    public void testExecuteQuery() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeQuery();
    }

    @Test(expectedExceptions = SQLException.class)
    public void testExecuteQueryWithException() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(SQLException.class).when(preparedStatement).executeQuery();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatement.executeQuery();

    }

    public void testExecuteUpdate() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeUpdate();
    }

    @Test(expectedExceptions = SQLException.class)
    public void testExecuteUpdateWithException() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeUpdate();
    }

    public void testExecute() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.execute()).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.execute();
    }

    @Test(expectedExceptions = SQLException.class)
    public void testExecuteWithException() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(SQLException.class).when(preparedStatement).execute();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.execute();
    }

    public void testSetNull() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNull(1, 1);
    }

    public void testSetBoolean() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBoolean(1, true);
    }

    public void testSetByte() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setByte(1, (byte) 127);
    }

    public void testSetShort() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setShort(1, new Short("12"));
    }

    public void testSetInt() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setInt(1, 1);
    }

    public void testSetLong() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setLong(1, 1);
    }

    public void testSetFloat() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setFloat(1, 1);
    }

    public void testSetDouble() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setDouble(1, 1.0);
    }

    public void testSetBigDecimal() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBigDecimal(1, new BigDecimal(1));
    }

    public void testSetString() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setString(1, "test");
    }

    public void testSetBytes() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBytes(1, "test".getBytes());
    }

    public void testSetDate() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setDate(1, new Date(2013, 10, 1));
    }

    public void testSetTime() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setTime(1, new Time(2013, 10, 1));
    }

    public void testSetTimestamp() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setTimestamp(1, new Timestamp(2013));
    }

    public void testSetAsciiStream() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setAsciiStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetUnicodeStream() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setUnicodeStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetBinaryStream() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBinaryStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testClearParameters() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).clearParameters();

        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.clearParameters();
    }

    public void testSetObject() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setObject(1, new Object());
    }

    public void testSetObjectWithTargetSqlType() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setObject(1, new Object(), 1);
    }

    public void testAddBatch() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.addBatch();
    }

    public void testAddBatchWithSql() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.addBatch("sql");
    }

    public void testSetCharacterStreamWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setCharacterStream(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetCharacterStream() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setCharacterStream(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetRef() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setRef(1, new Ref() {
            @Override
            public String getBaseTypeName() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getObject(Map<String, Class<?>> map) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getObject() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setObject(Object value) throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void tetSetBlob() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBlob(1, new Blob() {
            @Override
            public long length() throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public byte[] getBytes(long pos, int length) throws SQLException {
                return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public InputStream getBinaryStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long position(byte[] pattern, long start) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long position(Blob pattern, long start) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int setBytes(long pos, byte[] bytes) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public OutputStream setBinaryStream(long pos) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void truncate(long len) throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void free() throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public InputStream getBinaryStream(long pos, long length) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetClob() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setClob(1, new Clob() {
            @Override
            public long length() throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getSubString(long pos, int length) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Reader getCharacterStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public InputStream getAsciiStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long position(String searchstr, long start) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long position(Clob searchstr, long start) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int setString(long pos, String str) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int setString(long pos, String str, int offset, int len) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public OutputStream setAsciiStream(long pos) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Writer setCharacterStream(long pos) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void truncate(long len) throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void free() throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Reader getCharacterStream(long pos, long length) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetClobWithReader() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setClob(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetArray() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setArray(1, new Array() {
            @Override
            public String getBaseTypeName() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getBaseType() throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getArray() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getArray(Map<String, Class<?>> map) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getArray(long index, int count) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ResultSet getResultSet() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ResultSet getResultSet(long index, int count) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void free() throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testGetMetaData() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getMetaData()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getMetaData();
    }


    public void testSetDateWithCal() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setDate(1, new Date(2013, 10, 1), new Calendar() {
            @Override
            protected void computeTime() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected void computeFields() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void add(int field, int amount) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void roll(int field, boolean up) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getMinimum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getMaximum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetTimeWithCal() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setTime(1, new Time(2013, 10, 10), new Calendar() {
            @Override
            protected void computeTime() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected void computeFields() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void add(int field, int amount) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void roll(int field, boolean up) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getMinimum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getMaximum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }


    public void testSetTimestampWithCal() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setTimestamp(1, new Timestamp(2013), new Calendar() {
            @Override
            protected void computeTime() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected void computeFields() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void add(int field, int amount) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void roll(int field, boolean up) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getMinimum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getMaximum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetNullWithTypeName() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNull(1, 1, "test");
    }


    public void testSetURL() throws SQLException, MalformedURLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setURL(1, new URL("http://www.yahoo.com/"));
    }


    public void testGetParameterMetaData() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getMetaData()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getParameterMetaData();
    }

    public void testSetRowId() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setRowId(0, new RowId() {
            @Override
            public byte[] getBytes() {
                return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }


    public void testSetNString() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNString(1, "test");
    }

    public void testSetNCharacterStreamWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNCharacterStream(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetNCharacterStream() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNCharacterStream(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetNClob() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNClob(1, new NClob() {
            @Override
            public long length() throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getSubString(long pos, int length) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Reader getCharacterStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public InputStream getAsciiStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long position(String searchstr, long start) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long position(Clob searchstr, long start) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int setString(long pos, String str) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int setString(long pos, String str, int offset, int len) throws SQLException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public OutputStream setAsciiStream(long pos) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Writer setCharacterStream(long pos) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void truncate(long len) throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void free() throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Reader getCharacterStream(long pos, long length) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetClobWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setClob(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetBlobWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBlob(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetBlob() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBlob(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetNClobWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNClob(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetNClobWithReader() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setNClob(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testSetSQLXML() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setSQLXML(1, new SQLXML() {
            @Override
            public void free() throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public InputStream getBinaryStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public OutputStream setBinaryStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Reader getCharacterStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Writer setCharacterStream() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getString() throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setString(String value) throws SQLException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public <T extends Source> T getSource(Class<T> sourceClass) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public <T extends Result> T setResult(Class<T> resultClass) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }


    public void testSetObjectWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setObject(1, new Object(), 1, 1);
    }

    public void testSetAsciiStreamWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setAsciiStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetBinaryStreamWithLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBinaryStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }

    public void testSetCharacterStreamWithLenght() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setCharacterStream(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1);
    }


    public void testExecuteQueryWithSql() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery(anyString())).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeQuery("sql");
    }

    public void testExecuteUpdateWithSql() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate(anyString())).thenReturn(1);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeUpdate("sql");
    }

    public void testClose() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).close();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.close();
    }

    public void testGetMaxFieldSize() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getMaxFieldSize()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getMaxFieldSize();
    }

    public void testSetMaxFieldSize() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setMaxFieldSize(100);
    }


    public void getMaxRows() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getMaxRows()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getMaxRows();
    }

    public void testSetMaxRows() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setMaxRows(100);
    }

    public void testSetEscapeProcessing() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setEscapeProcessing(true);
    }

    public void testGetQueryTimeout() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getQueryTimeout()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getQueryTimeout();
    }

    public void testSetQueryTimeout() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setQueryTimeout(100);
    }

    public void testCancel() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).cancel();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.cancel();
    }

    public void getWarnings() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getWarnings()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).clearWarnings();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.clearWarnings();
    }

    public void testSetCursorName() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setCursorName("test");
    }

    public void testExecuteWithSql() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.execute(anyString())).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.execute("sql");
    }

    public void tetGetResultSet() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getResultSet()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getResultSet();
    }

    public void testGetUpdateCount() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getUpdateCount()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getUpdateCount();
    }

    public void testGetMoreResults() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getMoreResults()).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getMoreResults();
    }

    public void testSetFetchDirection() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setFetchDirection(100);
    }

    public void getFetchDirection() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getFetchDirection()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getFetchDirection();
    }

    public void testSetFetchSize() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setFetchSize(100);
    }

    public void testGetFetchSize() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getFetchSize()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getFetchSize();
    }

    public void testGetResultSetConcurrency() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getResultSetConcurrency()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getResultSetConcurrency();
    }

    public void testGetResultSetType() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getResultSetType()).thenReturn(100);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getResultSetType();
    }

    public void testClearBatch() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).clearBatch();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.clearBatch();
    }

    public void testExecuteBatch() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeBatch()).thenReturn(new int[3]);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeBatch();
    }

    public void testGetConnection() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getConnection()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getConnection();
    }

    public void testGetMoreResultsWithPara() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getMoreResults(anyInt())).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getMoreResults(1);
    }

    public void testGetGeneratedKeys() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getGeneratedKeys()).thenReturn(null);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getGeneratedKeys();
    }

    public void testExecuteUpdateWithSQLAndKey() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate(anyString(), anyInt())).thenReturn(10);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeUpdate("sql", 123);
    }

    public void testExecuteUpdateWithColumnInt() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        int[] arrayInt = new int[3];
        when(preparedStatement.executeUpdate(anyString(), any(int[].class))).thenReturn(10);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeUpdate("sql", arrayInt);
    }

    public void testExecuteUpdateWithColumnString() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        String[] arrayString = new String[3];
        when(preparedStatement.executeUpdate(anyString(), any(String[].class))).thenReturn(10);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.executeUpdate("sql", arrayString);
    }

    public void testExecuteWithGeneratedKeys() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.execute(anyString(), anyInt())).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.execute("sql", 123);
    }

    public void testExecuteWithColumnInt() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        int[] arrayInt = new int[3];
        when(preparedStatement.execute(anyString(), any(int[].class))).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.execute("sql", arrayInt);
    }

    public void testExecuteWithColumnString() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        String[] arrayString = new String[3];
        when(preparedStatement.execute(anyString(), any(String[].class))).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.execute("sql", arrayString);
    }

    public void testGetResultSetHoldability() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getResultSetHoldability()).thenReturn(10);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.getResultSetHoldability();
    }

    public void testIsClosed() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.isClosed()).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.isClosed();
    }

    public void testSetPoolable() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setPoolable(true);
    }

    public void testIsPoolable() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.isPoolable()).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.isPoolable();
    }

    public void testCloseOnCompletion() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).closeOnCompletion();
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.closeOnCompletion();
    }

    public void testIsCloseOnCompletion() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.isCloseOnCompletion()).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.isCloseOnCompletion();
    }

    public void testSetAsciiStreamWithLongLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setAsciiStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1L);
    }

    public void testSetBinaryStreamWithLongLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setBinaryStream(1, new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1L);
    }

    public void testSetCharacterStreamWithLongLength() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.setCharacterStream(1, new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void close() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }, 1L);
    }

    public void testUnwrap() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.unwrap(any(Class.class))).thenReturn(Class.class);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.unwrap(Class.class);
    }

    public void testIsWrapperFor() throws SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.isWrapperFor(any(Class.class))).thenReturn(true);
        PreparedStatementWrapper preparedStatementWrapper = new PreparedStatementWrapper("sql", preparedStatement);
        preparedStatementWrapper.isWrapperFor(Class.class);
    }
}
