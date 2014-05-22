package template.ws.data;


import org.testng.annotations.Test;
import template.data.ConnectionWrapper;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.Executor;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: zzh
 * Date: 13-10-30
 * Time: PM6:30
 * To change this template use File | Settings | File Templates.
 */
@Test(groups = {"unit_test"})
public class ConnectionWrapperTest {

    @Test
    public void testConstruct() throws Exception {
        Connection cs = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        Statement ss = mock(Statement.class);

        //purely for improving code coverage
        when(cs.createStatement()).thenReturn(ss);
        when(cs.prepareStatement(anyString())).thenReturn(ps);
        when(cs.prepareStatement(anyString(), anyInt())).thenReturn(ps);
        when(cs.nativeSQL(anyString())).thenReturn("native sql");
        when(cs.getAutoCommit()).thenReturn(true);
        doNothing().when(cs).setAutoCommit(anyBoolean());
        doNothing().when(cs).commit();
        doNothing().when(cs).rollback();
        doNothing().when(cs).close();
        when(cs.isClosed()).thenReturn(true);
        doNothing().when(cs).setReadOnly(anyBoolean());
        when(cs.isReadOnly()).thenReturn(true);
        doNothing().when(cs).setCatalog(anyString());
        when(cs.getCatalog()).thenReturn("get cat log");
        doNothing().when(cs).setTransactionIsolation(anyInt());
        when(cs.getTransactionIsolation()).thenReturn(2);
        doNothing().when(cs).clearWarnings();
        when(cs.createStatement(anyInt(), anyInt())).thenReturn(ss);
        when(cs.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(ps);
        doNothing().when(cs).setHoldability(anyInt());
        when(cs.getHoldability()).thenReturn(1);

        when(cs.createStatement(anyInt(), anyInt(), anyInt())).thenReturn(ss);
        when(cs.prepareStatement(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(ps);
        int aa[] = new int[3];
        when(cs.prepareStatement("sql", aa)).thenReturn(ps);
        String sstr[] = new String[2];
        when(cs.prepareStatement("sql", sstr)).thenReturn(ps);
        Blob bb = mock(Blob.class);
        when(cs.createBlob()).thenReturn(bb);
        NClob nc = mock(NClob.class);
        when(cs.createNClob()).thenReturn(nc);
        SQLXML xmlsql = mock(SQLXML.class);
        when(cs.createSQLXML()).thenReturn(xmlsql);
        when(cs.isValid(anyInt())).thenReturn(true);
        doNothing().when(cs).setClientInfo(anyString(), anyString());
        Properties pm = mock(Properties.class);
        doNothing().when(cs).setClientInfo(pm);
        when(cs.getClientInfo(anyString())).thenReturn("value");
        when(cs.getClientInfo()).thenReturn(pm);
        Object[] objs = new Object[3];
        Array arr = mock(Array.class);
        when(cs.createArrayOf("name", objs)).thenReturn(arr);
        Struct strt = mock(Struct.class);
        when(cs.createStruct("name", objs)).thenReturn(strt);
        doNothing().when(cs).setSchema(anyString());
        when(cs.getSchema()).thenReturn("schema");

        Executor exe = mock(Executor.class);
        doNothing().when(cs).abort(exe);
        doNothing().when(cs).setNetworkTimeout(exe, 2);
        when(cs.getNetworkTimeout()).thenReturn(anyInt());


        ConnectionWrapper cw = new ConnectionWrapper(cs);
        Statement st = cw.prepareStatement("sql");
        st = cw.prepareStatement("sql", 1);
        String sql = cw.nativeSQL("sql");
        cw.setAutoCommit(true);
        boolean r = cw.getAutoCommit();
        cw.commit();
        cw.rollback();
        cw.close();
        cw.isClosed();
        cw.setReadOnly(true);
        cw.isReadOnly();
        cw.setCatalog("catalog");
        cw.getCatalog();
        cw.setTransactionIsolation(1);
        cw.getTransactionIsolation();
        cw.clearWarnings();
        cw.createStatement(1, 2);
        cw.prepareStatement("sql", 1, 1);
        cw.setHoldability(1);
        cw.getHoldability();
        cw.createStatement(1, 2, 2);
        cw.prepareStatement("sql", 1, 1, 1);
        cw.prepareStatement("sql", aa);
        cw.prepareStatement("sql", sstr);
        cw.createBlob();
        cw.createNClob();
        cw.createSQLXML();
        cw.isValid(2);
        cw.setClientInfo("name", "value");
        cw.setClientInfo(pm);
        cw.getClientInfo("name");
        cw.getClientInfo();
        cw.createArrayOf("name", objs);
        cw.createStruct("name", objs);
        cw.setSchema("schema");
        cw.getSchema();
        cw.abort(exe);
        cw.setNetworkTimeout(exe, 2);
        cw.getNetworkTimeout();

    }

}
