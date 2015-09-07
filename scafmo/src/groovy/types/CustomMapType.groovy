package types

import org.hibernate.usertype.UserType
import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import grails.converters.JSON

class CustomMapType implements UserType {

	@Override
	public Class<CustomMap> returnedClass() {
		return CustomMap.class;
	}

	@Override
	public int[] sqlTypes() {
		return [SQLTYPE] as int[];
	}
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException{
		String jsonString = (String) rs.getObject(names[0]);
		return new CustomMap(JSON.parse(jsonString));
	}
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		Hibernate.STRING.nullSafeSet(preparedStatement,
				(value != null) ? value.toString() : null, index);
	}

	/* "default" implementations */

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return ObjectUtils.equals(x, y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		assert (x != null);
		return x.hashCode();
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return JSON.parse((String) cached) as CustomMap;
	}


}
