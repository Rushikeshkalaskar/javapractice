class Table{
	public synchronized void showTable(int x){
		try{
			for(int i=1;i<=10;i++){
				System.out.println(i + " X " + x + " = " + (i*x));
				Thread.sleep(1000);
		}}
			catch(Exception ex){
				System.out.println("error is "+ ex);
			}
		}
	}

class Two extends Thread{
	Table table;
	void setTable(Table table){
		this.table=table;
	}
	public void run(){
		table.showTable(2);
	}
}
class Three extends Thread{
	Table table;
	void setTable(Table table){
		this.table=table;
	}
	public void run(){
		table.showTable(3);
	}
}
public class TabSyncAsyncApp{
	public static void main(String []args){
		Table t=new Table();
		
		Three th=new Three();
		th.setTable(t);
		th.start();
		Two tw=new Two();
		tw.setTable(t);
		tw.start();
	}
}
-- =====================================================
-- Q1. Display members who have borrowed at least one book
-- =====================================================

SELECT
    m.member_name,
    b.book_name,
    b.author,
    br.borrow_date,
    br.return_date
FROM Members m
INNER JOIN BorrowRecords br
    ON m.member_id = br.member_id
INNER JOIN Books b
    ON br.book_id = b.book_id;


-- =====================================================
-- Q2. Members who have never borrowed any book
-- =====================================================

SELECT m.*
FROM Members m
LEFT JOIN BorrowRecords br
    ON m.member_id = br.member_id
WHERE br.borrow_id IS NULL;


-- =====================================================
-- Q3. Books borrowed more than once
-- Sort by borrowing count descending
-- =====================================================

SELECT
    b.book_name,
    COUNT(br.borrow_id) AS borrowing_count
FROM Books b
INNER JOIN BorrowRecords br
    ON b.book_id = br.book_id
GROUP BY b.book_id, b.book_name
HAVING COUNT(br.borrow_id) > 1
ORDER BY borrowing_count DESC;


-- =====================================================
-- Q4. Members whose borrowing count is greater than
-- the average borrowing count per member
-- Includes members with zero borrowings in the average
-- =====================================================

SELECT
    m.member_name,
    COUNT(br.borrow_id) AS borrowing_count
FROM Members m
LEFT JOIN BorrowRecords br
    ON m.member_id = br.member_id
GROUP BY m.member_id, m.member_name
HAVING COUNT(br.borrow_id) >
(
    SELECT COUNT(*) * 1.0 /
           (SELECT COUNT(*) FROM Members)
    FROM BorrowRecords
);


-- =====================================================
-- Q5. Books priced above their category's average price
-- =====================================================

SELECT
    b.book_name,
    b.category,
    b.price
FROM Books b
WHERE b.price >
(
    SELECT AVG(b2.price)
    FROM Books b2
    WHERE b2.category = b.category
);


-- =====================================================
-- Q6. Members who borrowed every Programming book
-- =====================================================

SELECT
    m.member_id,
    m.member_name
FROM Members m
WHERE NOT EXISTS
(
    SELECT 1
    FROM Books b
    WHERE b.category = 'Programming'
      AND NOT EXISTS
      (
          SELECT 1
          FROM BorrowRecords br
          WHERE br.member_id = m.member_id
            AND br.book_id = b.book_id
      )
);


-- =====================================================
-- Q7. Create view for currently borrowed books
-- =====================================================

CREATE OR REPLACE VIEW CurrentBorrowedBooks AS
SELECT
    m.member_name,
    b.book_name,
    br.borrow_date,
    m.membership_type
FROM Members m
INNER JOIN BorrowRecords br
    ON m.member_id = br.member_id
INNER JOIN Books b
    ON br.book_id = b.book_id
WHERE br.return_date IS NULL;

-- Retrieve all records from the view
SELECT * FROM CurrentBorrowedBooks;


-- =====================================================
-- Q8. View showing total and currently unreturned books
-- for every member, including members with zero borrowings
-- =====================================================

CREATE OR REPLACE VIEW MemberBorrowingStats AS
SELECT
    m.member_id,
    m.member_name,
    COUNT(br.borrow_id) AS total_borrowed_books,
    SUM(
        CASE
            WHEN br.borrow_id IS NOT NULL
             AND br.return_date IS NULL
            THEN 1
            ELSE 0
        END
    ) AS total_currently_unreturned_books
FROM Members m
LEFT JOIN BorrowRecords br
    ON m.member_id = br.member_id
GROUP BY m.member_id, m.member_name;

-- Retrieve all member borrowing statistics
SELECT * FROM MemberBorrowingStats;


-- =====================================================
-- Q9. Stored procedure to get a member's borrowing history
-- =====================================================

DROP PROCEDURE IF EXISTS GetMemberBorrowingHistory;

DELIMITER //

CREATE PROCEDURE GetMemberBorrowingHistory(
    IN p_member_id INT
)
BEGIN
    SELECT
        m.member_name,
        b.book_name,
        br.borrow_date,
        br.return_date
    FROM Members m
    INNER JOIN BorrowRecords br
        ON m.member_id = br.member_id
    INNER JOIN Books b
        ON br.book_id = b.book_id
    WHERE m.member_id = p_member_id;
END //

DELIMITER ;

-- Execute for member ID 1
CALL GetMemberBorrowingHistory(1);


-- =====================================================
-- Q10. Stored procedure to find active members
-- with borrowing count >= the given minimum
-- =====================================================

DROP PROCEDURE IF EXISTS GetActiveMembers;

DELIMITER //

CREATE PROCEDURE GetActiveMembers(
    IN p_min_borrowing_count INT
)
BEGIN
    SELECT
        m.member_name,
        m.city,
        COUNT(br.borrow_id) AS total_borrowing_count
    FROM Members m
    INNER JOIN BorrowRecords br
        ON m.member_id = br.member_id
    GROUP BY m.member_id, m.member_name, m.city
    HAVING COUNT(br.borrow_id) >= p_min_borrowing_count;
END //

DELIMITER ;

-- Execute with minimum borrowing count = 2
CALL GetActiveMembers(2);


-- =====================================================
-- Q11. Members who borrowed from every available category
-- =====================================================

SELECT
    m.member_id,
    m.member_name
FROM Members m
WHERE NOT EXISTS
(
    SELECT 1
    FROM Books b
    WHERE b.category IS NOT NULL
      AND NOT EXISTS
      (
          SELECT 1
          FROM BorrowRecords br
          INNER JOIN Books b2
              ON br.book_id = b2.book_id
          WHERE br.member_id = m.member_id
            AND b2.category = b.category
      )
);


-- =====================================================
-- Q12. Book or books with the second-highest borrowing count
-- Handles ties without LIMIT
-- =====================================================

SELECT
    b.book_name,
    COUNT(br.borrow_id) AS borrowing_count
FROM Books b
LEFT JOIN BorrowRecords br
    ON b.book_id = br.book_id
GROUP BY b.book_id, b.book_name
HAVING
    COUNT(br.borrow_id) <
    (
        SELECT MAX(book_counts.borrowing_count)
        FROM
        (
            SELECT COUNT(br2.borrow_id) AS borrowing_count
            FROM Books b2
            LEFT JOIN BorrowRecords br2
                ON b2.book_id = br2.book_id
            GROUP BY b2.book_id
        ) AS book_counts
    )
    AND COUNT(br.borrow_id) =
    (
        SELECT MAX(book_counts.borrowing_count)
        FROM
        (
            SELECT COUNT(br3.borrow_id) AS borrowing_count
            FROM Books b3
            LEFT JOIN BorrowRecords br3
                ON b3.book_id = br3.book_id
            GROUP BY b3.book_id
        ) AS book_counts
        WHERE book_counts.borrowing_count <
        (
            SELECT MAX(book_counts2.borrowing_count)
            FROM
            (
                SELECT COUNT(br4.borrow_id) AS borrowing_count
                FROM Books b4
                LEFT JOIN BorrowRecords br4
                    ON b4.book_id = br4.book_id
                GROUP BY b4.book_id
            ) AS book_counts2
        )
    );