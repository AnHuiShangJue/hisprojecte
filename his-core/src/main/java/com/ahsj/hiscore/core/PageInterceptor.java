package com.ahsj.hiscore.core;

import core.entity.BaseEntity;
import core.entity.PageBean;
import core.entity.PageParameter;
import core.entity.Principal;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import utils.EmptyUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: squarenavigationsys
 * @description:PageInterceptor
 * @author: chenzhicai
 * @create: 2018-11-12-01-01
 **/

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    private static final Log logger = LogFactory.getLog(PageInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private static String defaultDialect = "mysql";
    private static String defaultPageSqlId = ".*Page$";
    private static String dialect = "";
    private static String pageSqlId = "";

    public PageInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaStatementHandler;
        Object object;
        for (metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY); metaStatementHandler.hasGetter("h"); metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY)) {
            object = metaStatementHandler.getValue("h");
        }

        while (metaStatementHandler.hasGetter("target")) {
            object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }

        if (dialect == null || "".equals(dialect)) {
            logger.warn("Property dialect is not setted,use default 'mysql' ");
            dialect = defaultDialect;
        }

        if (pageSqlId == null || "".equals(pageSqlId)) {
            logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
            pageSqlId = defaultPageSqlId;
        }

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject != null) {
            boolean flag = false;
            BaseEntity baseEntity = null;

            try {
                baseEntity = (BaseEntity) ((PageBean) parameterObject).getParameter();
                if (baseEntity.getStart() != -1) {
                    flag = true;
                }
            } catch (Exception var13) {
                ;
            }

            if (flag) {
                PageParameter page = new PageParameter();
                page.setCurrentPage(baseEntity.getStart());
                page.setPageSize(baseEntity.getLength());
                String sql = boundSql.getSql();
                String pageSql = this.buildPageSql(sql, page);
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                metaStatementHandler.setValue("delegate.rowBounds.offset", 0);
                metaStatementHandler.setValue("delegate.rowBounds.limit", 2147483647);
                Connection connection = (Connection) invocation.getArgs()[0];
                this.setPageParameter(sql, connection, mappedStatement, boundSql, page);
                ((PageBean) parameterObject).setRecordsTotal(page.getRecordsTotal());
                ((PageBean) parameterObject).setRecordsFiltered(page.getRecordsTotal());
                ((PageBean) parameterObject).setDraw(baseEntity.getDraw());
            } else if (parameterObject instanceof BaseEntity) {
                BaseEntity insert = (BaseEntity) parameterObject;
                if (insert.isNeedAutoAddIU()) {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if(EmptyUtil.Companion.isNullOrEmpty(authentication)) return invocation.proceed();
                    Principal principal = (Principal) authentication.getPrincipal();
                    if (EmptyUtil.Companion.isNullOrEmpty(insert.getCompanyId())) {
                        insert.setCompanyId(principal.getCompanyId());
                    }

                    if (EmptyUtil.Companion.isNullOrEmpty(insert.getDelFlag())) {
                        insert.setDelFlag("0");
                    }

                    if (EmptyUtil.Companion.isNullOrEmpty(insert.getVersionNo())) {
                        insert.setVersionNo(0);
                    }

                    insert.setCreateDate(Calendar.getInstance().getTime());
                    if (principal != null) {
                        insert.setCreateUserId(principal.getId().toString());
                        insert.setUpdateUserId(principal.getId().toString());
                    }

                    insert.setUpdateDate(Calendar.getInstance().getTime());
                }
            } else if (parameterObject instanceof Map) {
                Map<String, List> map = (Map<String, List>) parameterObject;
                if (map.containsKey("list") && map.get("list") instanceof List) {
                    try {
                        List<BaseEntity> list = (List<BaseEntity>) map.get("list");
                        for (BaseEntity insert : list) {
                            if (insert.isNeedAutoAddIU()) {
                                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                                if(EmptyUtil.Companion.isNullOrEmpty(authentication)) return invocation.proceed();
                                Principal principal = (Principal) authentication.getPrincipal();
                                if (EmptyUtil.Companion.isNullOrEmpty(insert.getCompanyId())) {
                                    insert.setCompanyId(principal.getCompanyId());
                                }

                                if (EmptyUtil.Companion.isNullOrEmpty(insert.getDelFlag())) {
                                    insert.setDelFlag("0");
                                }

                                if (EmptyUtil.Companion.isNullOrEmpty(insert.getVersionNo())) {
                                    insert.setVersionNo(0);
                                }

                                insert.setCreateDate(Calendar.getInstance().getTime());
                                if (principal != null) {
                                    insert.setCreateUserId(principal.getId().toString());
                                    insert.setUpdateUserId(principal.getId().toString());
                                }

                                insert.setUpdateDate(Calendar.getInstance().getTime());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ;
                    } finally {
                        return invocation.proceed();
                    }
                }
            }
        }

        return invocation.proceed();
    }

    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, PageParameter page) {
        String countSql = "select count(0) from (" + sql + ") as total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;

        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            this.setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            Long totalCount = 0L;
            if (rs.next()) {
                totalCount = rs.getLong(1);
            }

            page.setRecordsTotal(totalCount);
        } catch (Exception var23) {
            logger.error("Ignore this exception", var23);
        } finally {
            try {
                rs.close();
            } catch (SQLException var22) {
                logger.error("Ignore this exception", var22);
            }

            try {
                countStmt.close();
            } catch (SQLException var21) {
                logger.error("Ignore this exception", var21);
            }

        }

    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    private String buildPageSql(String sql, PageParameter page) {
        if (page != null) {
            new StringBuilder();
            StringBuilder pageSql;
            if ("mysql".equals(dialect)) {
                pageSql = this.buildPageSqlForMysql(sql, page);
            } else {
                if (!"oracle".equals(dialect)) {
                    return sql;
                }

                pageSql = this.buildPageSqlForOracle(sql, page);
            }

            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public StringBuilder buildPageSqlForMysql(String sql, PageParameter page) {
        StringBuilder pageSql = new StringBuilder(100);
        pageSql.append(sql);
        pageSql.append(" limit " + page.getCurrentPage() + "," + page.getPageSize());
        return pageSql;
    }

    public StringBuilder buildPageSqlForOracle(String sql, PageParameter page) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
        String endrow = String.valueOf(page.getCurrentPage() * page.getPageSize());
        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(" ) temp where rownum <= ").append(endrow);
        pageSql.append(") where row_id > ").append(beginrow);
        return pageSql;
    }

    public Object plugin(Object target) {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }

    public void setProperties(Properties properties) {
    }
}

    