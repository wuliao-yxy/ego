package com.hrm.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TClockInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TClockInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andClockIdIsNull() {
            addCriterion("clock_id is null");
            return (Criteria) this;
        }

        public Criteria andClockIdIsNotNull() {
            addCriterion("clock_id is not null");
            return (Criteria) this;
        }

        public Criteria andClockIdEqualTo(Integer value) {
            addCriterion("clock_id =", value, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdNotEqualTo(Integer value) {
            addCriterion("clock_id <>", value, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdGreaterThan(Integer value) {
            addCriterion("clock_id >", value, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("clock_id >=", value, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdLessThan(Integer value) {
            addCriterion("clock_id <", value, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdLessThanOrEqualTo(Integer value) {
            addCriterion("clock_id <=", value, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdIn(List<Integer> values) {
            addCriterion("clock_id in", values, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdNotIn(List<Integer> values) {
            addCriterion("clock_id not in", values, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdBetween(Integer value1, Integer value2) {
            addCriterion("clock_id between", value1, value2, "clockId");
            return (Criteria) this;
        }

        public Criteria andClockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("clock_id not between", value1, value2, "clockId");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIsNull() {
            addCriterion("employee_no is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIsNotNull() {
            addCriterion("employee_no is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoEqualTo(String value) {
            addCriterion("employee_no =", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotEqualTo(String value) {
            addCriterion("employee_no <>", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoGreaterThan(String value) {
            addCriterion("employee_no >", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoGreaterThanOrEqualTo(String value) {
            addCriterion("employee_no >=", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLessThan(String value) {
            addCriterion("employee_no <", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLessThanOrEqualTo(String value) {
            addCriterion("employee_no <=", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLike(String value) {
            addCriterion("employee_no like", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotLike(String value) {
            addCriterion("employee_no not like", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIn(List<String> values) {
            addCriterion("employee_no in", values, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotIn(List<String> values) {
            addCriterion("employee_no not in", values, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoBetween(String value1, String value2) {
            addCriterion("employee_no between", value1, value2, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotBetween(String value1, String value2) {
            addCriterion("employee_no not between", value1, value2, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andClockInTimeIsNull() {
            addCriterion("clock_in_time is null");
            return (Criteria) this;
        }

        public Criteria andClockInTimeIsNotNull() {
            addCriterion("clock_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andClockInTimeEqualTo(Date value) {
            addCriterion("clock_in_time =", value, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeNotEqualTo(Date value) {
            addCriterion("clock_in_time <>", value, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeGreaterThan(Date value) {
            addCriterion("clock_in_time >", value, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("clock_in_time >=", value, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeLessThan(Date value) {
            addCriterion("clock_in_time <", value, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeLessThanOrEqualTo(Date value) {
            addCriterion("clock_in_time <=", value, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeIn(List<Date> values) {
            addCriterion("clock_in_time in", values, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeNotIn(List<Date> values) {
            addCriterion("clock_in_time not in", values, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeBetween(Date value1, Date value2) {
            addCriterion("clock_in_time between", value1, value2, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockInTimeNotBetween(Date value1, Date value2) {
            addCriterion("clock_in_time not between", value1, value2, "clockInTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeIsNull() {
            addCriterion("clock_off_time is null");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeIsNotNull() {
            addCriterion("clock_off_time is not null");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeEqualTo(Date value) {
            addCriterion("clock_off_time =", value, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeNotEqualTo(Date value) {
            addCriterion("clock_off_time <>", value, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeGreaterThan(Date value) {
            addCriterion("clock_off_time >", value, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("clock_off_time >=", value, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeLessThan(Date value) {
            addCriterion("clock_off_time <", value, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeLessThanOrEqualTo(Date value) {
            addCriterion("clock_off_time <=", value, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeIn(List<Date> values) {
            addCriterion("clock_off_time in", values, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeNotIn(List<Date> values) {
            addCriterion("clock_off_time not in", values, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeBetween(Date value1, Date value2) {
            addCriterion("clock_off_time between", value1, value2, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockOffTimeNotBetween(Date value1, Date value2) {
            addCriterion("clock_off_time not between", value1, value2, "clockOffTime");
            return (Criteria) this;
        }

        public Criteria andClockDateIsNull() {
            addCriterion("clock_date is null");
            return (Criteria) this;
        }

        public Criteria andClockDateIsNotNull() {
            addCriterion("clock_date is not null");
            return (Criteria) this;
        }

        public Criteria andClockDateEqualTo(Date value) {
            addCriterionForJDBCDate("clock_date =", value, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("clock_date <>", value, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateGreaterThan(Date value) {
            addCriterionForJDBCDate("clock_date >", value, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("clock_date >=", value, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateLessThan(Date value) {
            addCriterionForJDBCDate("clock_date <", value, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("clock_date <=", value, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateIn(List<Date> values) {
            addCriterionForJDBCDate("clock_date in", values, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("clock_date not in", values, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("clock_date between", value1, value2, "clockDate");
            return (Criteria) this;
        }

        public Criteria andClockDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("clock_date not between", value1, value2, "clockDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}