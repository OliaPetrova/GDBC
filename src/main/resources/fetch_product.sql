SELECT O.product_name
FROM ORDERS O
JOIN CUSTOMERS C ON O.customer_id = C.id
WHERE LOWER(C.name) = LOWER(:name);