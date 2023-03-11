INSERT INTO book_order (id, user_id, order_number, total_price, status,
                        created_at, updated_at)
VALUES ('c0a80064-85a4-12fa-8185-a443166d0000', '1', 'OD230112123538904', 62.00, 'CREATED',
        '2023-01-12 12:35:38', '2023-01-12 12:35:38'),
       ('c0a80064-85a4-12fa-8185-a4444c7c0003', '2', 'OD230112123657158', 155.00, 'CREATED',
        '2023-01-12 12:36:58', '2023-01-12 12:36:58'),
       ('c0a80064-85a4-12fa-8185-a444cf810007', '2', 'OD230112123731331', 30.00, 'COMPLETED',
       '2023-01-12 12:37:31', '2023-01-12 12:39:26'),
       ('c0a80064-85a4-12fa-8185-a4454da10009', '2', 'OD23011212380330', 18.00, 'CANCELLED',
      '2023-01-12 12:38:03', '2023-01-12 12:39:37'),
       ('c0a80064-85a4-12fa-8185-a445644a000b', '2', 'OD230112123809868', 10.00, 'CREATED',
       '2023-01-12 12:38:09', '2023-01-12 12:38:09');

INSERT INTO order_item (id, order_id, book_id, quantity, deal_price, created_at, updated_at)
VALUES ('c0a80064-85a4-12fa-8185-a44316870001', 'c0a80064-85a4-12fa-8185-a443166d0000',
        'be676d3c-9231-11ed-8022-dfbc2ad29930', '1', 30.00, '2023-01-12 12:35:38',
        '2023-01-12 12:35:38'),
       ('c0a80064-85a4-12fa-8185-a44316880002', 'c0a80064-85a4-12fa-8185-a443166d0000',
        'be676db4-9231-11ed-8024-f38f4db9d96a', '2', 16.00, '2023-01-12 12:35:38',
        '2023-01-12 12:35:38'),
       ('c0a80064-85a4-12fa-8185-a4444c7d0004', 'c0a80064-85a4-12fa-8185-a4444c7c0003',
        'be676d3c-9231-11ed-8022-dfbc2ad29930', '3', 30.00, '2023-01-12 12:36:58',
        '2023-01-12 12:36:58'),
       ('c0a80064-85a4-12fa-8185-a4444c7d0005', 'c0a80064-85a4-12fa-8185-a4444c7c0003',
        'be676db4-9231-11ed-8024-f38f4db9d96a', '1', 16.00, '2023-01-12 12:36:58',
        '2023-01-12 12:36:58'),
       ('c0a80064-85a4-12fa-8185-a4444c7e0006', 'c0a80064-85a4-12fa-8185-a4444c7c0003',
        'be676d00-9231-11ed-8021-4bc2600afe8e', '1', 49.00, '2023-01-12 12:36:58',
        '2023-01-12 12:36:58'),
       ('c0a80064-85a4-12fa-8185-a444cf810008', 'c0a80064-85a4-12fa-8185-a444cf810007',
        'be676d3c-9231-11ed-8022-dfbc2ad29930', '1', 30.00, '2023-01-12 12:37:31',
        '2023-01-12 12:37:31'),
       ('c0a80064-85a4-12fa-8185-a4454da2000a', 'c0a80064-85a4-12fa-8185-a4454da10009',
        'be676db4-9231-11ed-8024-f38f4db9d96a', '1', 18.00, '2023-01-12 12:38:03',
        '2023-01-12 12:38:03'),
       ('c0a80064-85a4-12fa-8185-a445644b000c', 'c0a80064-85a4-12fa-8185-a445644a000b',
        'be676db4-9231-11ed-8024-f38f4db9d96a', '1', 10.00, '2023-01-12 12:38:09',
        '2023-01-12 12:38:09');