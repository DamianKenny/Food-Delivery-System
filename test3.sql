CREATE TABLE SUPPLIERS (
    SUPPLIERID    NUMBER         NOT NULL,
    NAME          VARCHAR2(100),
    CONTACTEMAIL  VARCHAR2(100),
    PHONE         VARCHAR2(20),
    ADDRESS       VARCHAR2(200),
    CITY          VARCHAR2(50),
    COUNTRY       VARCHAR2(50),
    CONSTRAINT PK_SUPPLIERS PRIMARY KEY (SUPPLIERID)
);

CREATE TABLE PRODUCTS (
    PRODUCTID     NUMBER          NOT NULL,
    NAME          VARCHAR2(100),
    CATEGORY      VARCHAR2(50),
    PRICE         FLOAT(53),
    STOCK         NUMBER,
    PRODUCTIMAGE  BLOB,
    IMAGE_TYPE    VARCHAR2(255 CHAR),
    CONSTRAINT PK_PRODUCTS PRIMARY KEY (PRODUCTID)
);

CREATE TABLE ORDERS (
    ID              NUMBER(19)      NOT NULL,
    ORDER_DATE      TIMESTAMP(6),
    PAYMENT_METHOD  VARCHAR2(255 CHAR),
    TOTAL           FLOAT(53),
    PRODUCTS        CLOB,
    TOTAL_ITEMS     NUMBER(10),
    CONSTRAINT PK_ORDERS PRIMARY KEY (ID)
);

CREATE TABLE CUSTOMERS (
    ID        NUMBER(38)     NOT NULL,
    NAME      VARCHAR2(255),
    EMAIL     VARCHAR2(255),
    PASSWORD  VARCHAR2(255),
    CONSTRAINT PK_CUSTOMERS PRIMARY KEY (ID)
);

select * from customers;

CREATE OR REPLACE PROCEDURE GET_ALL_PRODUCTS (p_cursor OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM PRODUCTS;
END GET_ALL_PRODUCTS;

CREATE OR REPLACE PROCEDURE SEARCH_BY_NAME (
    p_name IN VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM PRODUCTS
        WHERE UPPER(NAME) LIKE UPPER('%' || p_name || '%');
END SEARCH_BY_NAME;


CREATE OR REPLACE PROCEDURE SEARCH_BY_CATEGORY (
    p_category IN VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM PRODUCTS
        WHERE UPPER(CATEGORY) LIKE UPPER('%' || p_category || '%');
END SEARCH_BY_CATEGORY;


CREATE OR REPLACE PROCEDURE ADD_PRODUCT (
    p_name IN VARCHAR2,
    p_category IN VARCHAR2,
    p_price IN NUMBER,
    p_stock IN NUMBER,
    p_product_image IN BLOB,
    p_image_type IN VARCHAR2
) AS
BEGIN
    INSERT INTO PRODUCTS (NAME, CATEGORY, PRICE, STOCK, PRODUCTIMAGE, IMAGE_TYPE)
    VALUES (p_name, p_category, p_price, p_stock, p_product_image, p_image_type);
    COMMIT;
END ADD_PRODUCT;

--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE save_supplier (
    p_name IN VARCHAR2,
    p_contact_email IN VARCHAR2,
    p_phone IN VARCHAR2,
    p_address IN VARCHAR2,
    p_city IN VARCHAR2,
    p_country IN VARCHAR2,
    p_supplier_id OUT NUMBER
) AS
BEGIN
    
    SELECT SUPPLIER_SEQ.NEXTVAL INTO p_supplier_id FROM DUAL;
    
    
    INSERT INTO suppliers (
        SUPPLIERID,
        NAME,
        CONTACTEMAIL,
        PHONE,
        ADDRESS,
        CITY,
        COUNTRY
    ) VALUES (
        p_supplier_id,
        p_name,
        p_contact_email,
        p_phone,
        p_address,
        p_city,
        p_country
    );
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END save_supplier;
/

CREATE OR REPLACE PROCEDURE ADD_ORDER (
    p_item_names IN CLOB,        
    p_quantity IN NUMBER,        
    p_total_amount IN FLOAT,     
    p_payment_method IN VARCHAR2 
) AS
BEGIN
    
    INSERT INTO ORDERS (
        ID, ORDER_DATE, PAYMENT_METHOD, TOTAL, PRODUCTS, TOTAL_ITEMS
    )
    VALUES (
        ORDERS_SEQ.NEXTVAL,                
        SYSTIMESTAMP,                       
        p_payment_method,                  
        p_total_amount,                    
        p_item_names,                       
        p_quantity                         
    );
    
    COMMIT; 
END ADD_ORDER;







