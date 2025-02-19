INSERT INTO admin.tb_member(member_id, password) VALUES('yellowplace', '$2a$10$oWptnmRZYHVzIPSjuT0Breit2ir7l.0Ohff/zVnNY4zmnCTcwGLki');
INSERT INTO admin.tb_role
(role_id, role_description, role_nm, role_priorty)
VALUES(1, '관리자권한', 'admin', 1);
INSERT INTO admin.tb_role
(role_id, role_description, role_nm, role_priorty)
VALUES(2, '사용자권한', 'user', 2);
INSERT INTO admin.tb_member_role
(member_role_id, member_id, role_id)
VALUES(1, 'yellowplace', 1);
INSERT INTO admin.tb_member_role
(member_role_id, member_id, role_id)
VALUES(2, 'yellowplace', 2);
INSERT INTO admin.tb_menu
(menu_id, menu_name, menu_url)
VALUES(1, '메인', '/main');
INSERT INTO admin.tb_menu
(menu_id, menu_name, menu_url)
VALUES(2, '게시판', '/board');
INSERT INTO admin.tb_api
(api_id, api_url)
VALUES(1, '/auth/main');
INSERT INTO admin.tb_api
(api_id, api_url)
VALUES(2, '/auth/test');
INSERT INTO admin.tb_api
(api_id, api_url)
VALUES(3, '/board/list');
INSERT INTO admin.tb_api
(api_id, api_url)
VALUES(4, '/board/write');
INSERT INTO admin.tb_menu_api
(menu_api_id, menu_id, api_id)
VALUES(1, 1, 1);
INSERT INTO admin.tb_menu_api
(menu_api_id, menu_id, api_id)
VALUES(2, 1, 2);
INSERT INTO admin.tb_menu_api
(menu_api_id, menu_id, api_id)
VALUES(3, 2, 3);
INSERT INTO admin.tb_menu_api
(menu_api_id, menu_id, api_id)
VALUES(4, 2, 4);
INSERT INTO admin.tb_role_menu_api
(role_menu_api_id, menu_api_id, role_id)
VALUES(1, 1, 1);
INSERT INTO admin.tb_role_menu_api
(role_menu_api_id, menu_api_id, role_id)
VALUES(2, 2, 1);
INSERT INTO admin.tb_role_menu_api
(role_menu_api_id, menu_api_id, role_id)
VALUES(3, 3, 1);
INSERT INTO admin.tb_role_menu_api
(role_menu_api_id, menu_api_id, role_id)
VALUES(4, 1, 2);
INSERT INTO admin.tb_role_menu_api
(role_menu_api_id, menu_api_id, role_id)
VALUES(5, 2, 2);
INSERT INTO admin.tb_menuperm
(menu_perm_id, menu_perm_value, menu_id)
VALUES(1, 15, 1);
INSERT INTO admin.tb_menuperm
(menu_perm_id, menu_perm_value, menu_id)
VALUES(2, 15, 2);
INSERT INTO admin.tb_perm
(perm_id, perm_nm, perm_val)
VALUES(1, '조회', 1);
INSERT INTO admin.tb_perm
(perm_id, perm_nm, perm_val)
VALUES(2, '추가', 2);
INSERT INTO admin.tb_perm
(perm_id, perm_nm, perm_val)
VALUES(3, '수정', 4);
INSERT INTO admin.tb_perm
(perm_id, perm_nm, perm_val)
VALUES(4, '삭제', 8);
INSERT INTO admin.tb_role_menuperm
(role_menuperm_id, role_id, menu_perm_id)
VALUES(1, 1, 1);
INSERT INTO admin.tb_role_menuperm
(role_menuperm_id, role_id, menu_perm_id)
VALUES(2, 1, 2);
INSERT INTO admin.tb_role_menuperm
(role_menuperm_id, role_id, menu_perm_id)
VALUES(3, 2, 1);
INSERT INTO admin.tb_board
(board_id, board_nm, reg_id, created_at, upd_id, updated_at)
VALUES(1, '게시판A', 'admin', '2025-02-19 07:58:16.000', 'admin', '2025-02-19 07:58:16.000');
INSERT INTO admin.tb_board
(board_id, board_nm, reg_id, created_at, upd_id, updated_at)
VALUES(2, '게시판B', 'admin', '2025-02-19 07:58:16.000', 'admin', '2025-02-19 07:58:16.000');
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(1, '기사제목1', '내용1', 'admin', '2025-02-19 07:59:58.000', 'admin', '2025-02-19 07:59:58.000', 1);
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(2, '기사제목2', '내용2', 'admin', '2025-02-19 07:59:59.000', 'admin', '2025-02-19 07:59:59.000', 1);
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(3, '기사제목3', '내용3', 'admin', '2025-02-19 07:59:59.000', 'admin', '2025-02-19 07:59:59.000', 1);
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(4, '기사제목4', '내용4', 'admin', '2025-02-19 07:59:59.000', 'admin', '2025-02-19 07:59:59.000', 1);
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(5, '기사제목5', '내용5', 'admin', '2025-02-19 07:59:59.000', 'admin', '2025-02-19 07:59:59.000', 1);
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(6, '기사제목6', '내용6', 'admin', '2025-02-19 07:59:59.000', 'admin', '2025-02-19 07:59:59.000', 1);
INSERT INTO admin.tb_article
(article_id, article_title, article_content, reg_id, created_at, upd_id, updated_at, board_id)
VALUES(7, '기사제목7', '내용7', 'admin', '2025-02-19 07:59:59.000', 'admin', '2025-02-19 07:59:59.000', 1);