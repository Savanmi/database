DELIMITER //
CREATE FUNCTION get_age (birthdate DATE)
    RETURNS INT
    BEGIN
    declare age INT;
    SET age := floor(dateDIFF(CURDATE(), birthdate)/365);
        RETURN age;
    END;//

DELIMITER ;

CREATE OR REPLACE VIEW clients_ages AS
    SELECT
        CLIENT_ID,
        GET_AGE(BIRTH_DATE) AS AGE
    FROM
        CLIENTS

DELIMITER //
CREATE FUNCTION get_long_distance_debt_age(CURRENT_CALLER_ID INT)
    RETURNS INT
    BEGIN
    DECLARE age INT;
	DECLARE  debt_create_date DATE;

        SELECT B.LONG_DIST_DEBT_DATE
        INTO debt_create_date
        FROM BALANCES B
        WHERE CALLER_ID = CURRENT_CALLER_ID;

        SET age := DATEDIFF(CURDATE(), debt_create_date);
        RETURN age;
    END; //
    DELIMITER ;

CREATE FUNCTION get_subscription_debt_age(CURRENT_CALLER_ID INT)
    RETURNS INT
    BEGIN
    DECLARE age INT;
    DECLARE debt_create_date DATE;

        SELECT B.SUBSCRIPTION_DEBT_DATE
        INTO debt_create_date
        FROM BALANCES B
        WHERE CALLER_ID = CURRENT_CALLER_ID;

        SET age := DATEDIFF(CURDATE(), debt_create_date);
        RETURN age;
    END; //
    DELIMITER ;

CREATE OR REPLACE VIEW debtors_info AS
    SELECT
        DISTINCT
        TELEPHONE_EXCHANGE_ID,
        CALLER_ID,
        IS_BLOCKED,
        SECOND_NAME, FIRST_NAME, MIDDLE_NAME,
        COALESCE(SUBSCRIPTION_DEBT, 0) AS SUBSCRIPTION_DEBT,
        COALESCE(LONG_DISTANCE_CALLS_DEBT, 0) AS LD_CALLS_DEBT,
        GET_SUBSCRIPTION_DEBT_AGE(CALLER_ID)  AS SUBSCRIPTION_DEBT_AGE,
        GET_LONG_DISTANCE_DEBT_AGE(CALLER_ID) AS LONG_DISTANCE_DEBT_AGE
    FROM
        CALLERS CA
        JOIN CLIENTS CL USING (CLIENT_ID)
        LEFT JOIN BALANCES B USING (CALLER_ID)

CREATE OR REPLACE VIEW caller_addresses AS
    SELECT
        DISTINCT CALLER_ID,
        ADDR.ADDRESS_ID,
        REGION,
        STREET
    FROM
        CALLERS JOIN
        PHONES P USING(CALLER_ID) JOIN
        ADDRESS ADDR on ADDR.ADDRESS_ID = P.ADDRESS_IDcaller_addressescaller_addresses

DELIMITER //
CREATE FUNCTION is_debtor(CURRENT_CALLER_ID INT)
    RETURNS INT
    BEGIN
       DECLARE is_debtor INT;
       DECLARE long_distance_debt INT;
       DECLARE subscription_dept INT;

        SELECT LONG_DISTANCE_CALLS_DEBT
        INTO long_distance_debt
        FROM BALANCES
        WHERE CALLER_ID = CURRENT_CALLER_ID;

        SELECT SUBSCRIPTION_DEBT
        INTO subscription_dept
        FROM BALANCES
        WHERE CALLER_ID = CURRENT_CALLER_ID;

        IF long_distance_debt > 0 OR subscription_dept > 0 THEN
        SET is_debtor := 1;
        ELSE
        SET is_debtor := 0;
        END IF;

        RETURN is_debtor;
    END; //
    DELIMITER ;

DELIMITER //
CREATE FUNCTION get_debtor_count(EXCHANGE_ID INT)
    RETURNS INT
    BEGIN
        DECLARE debtor_count INT;

        SELECT COUNT(CALLER_ID)
        INTO debtor_count
        FROM CALLERS
        WHERE
        COALESCE(IS_DEBTOR(CALLER_ID), 0) = 1
        AND CALLERS.TELEPHONE_EXCHANGE_ID = EXCHANGE_ID;

        RETURN debtor_count;
    END; //
    DELIMITER ;

DELIMITER //
CREATE FUNCTION get_max_debt(EXCHANGE_ID INT)
    RETURNS INT
    BEGIN
       DECLARE max_debt INT;
        SELECT
            MAX(LONG_DISTANCE_CALLS_DEBT + SUBSCRIPTION_DEBT + PENALTY_INTEREST)
        INTO max_debt
        FROM
            BALANCES JOIN
            CALLERS CA on CA.CALLER_ID = BALANCES.CALLER_ID
        WHERE
            CA.TELEPHONE_EXCHANGE_ID = EXCHANGE_ID;

        RETURN max_debt;
    END; //
    DELIMITER ;

CREATE OR REPLACE VIEW FREE_PHONES_POSSIBILITIES AS
    SELECT
        DISTINCT
        PN.PHONE_NUMBER_ID,
        PN.PHONE_NUMBER,
        IP.TELEPHONE_EXCHANGE_ID,
        IP.ADDRESS_ID
    FROM
        PHONE_NUMBERS PN
        INNER JOIN INSTALLING_POSSIBILITIES IP ON IP.TELEPHONE_EXCHANGE_ID = PN.TELEPHONE_EXCHANGE_ID


    DELIMITER //
CREATE FUNCTION calculate_call_cost(start_date DATE, end_date DATE, price_per_min INT)
    RETURNs INT
    BEGIN
    DECLARE duration_min INT;

        SET duration_min := CEIL(24 * 60 * (end_date - start_date));
        RETURN duration_min * price_per_min;
    END; //
DELIMITER ;
