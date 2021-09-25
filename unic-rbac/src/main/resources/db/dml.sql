delete from sys_user where 1=1;
delete from sys_role where 1=1;
delete from sys_permission where 1=1;
delete from sys_user_role where 1=1;
delete from sys_role_permission where 1=1;
delete from sys_merchant where 1=1;

# 初始化系统管理菜单
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(1, '系统管理', 'folder', 'system_manage', NULL, 'el-icon-setting', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(2, '商户管理', 'page', 'merchant_manage', 1, 'el-icon-coin', '/merchantManage');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(3, '新增', 'button', 'merchant_manage_add', 2, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(4, '修改', 'button', 'merchant_manage_update', 2, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(5, '删除', 'button', 'merchant_manage_delete', 2, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(6, '用户管理', 'page', 'user_manage', 1, 'el-icon-s-custom', '/userManage');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(7, '新增', 'button', 'user_manage_add', 6, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(8, '修改', 'button', 'user_manage_update', 6, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(9, '删除', 'button', 'user_manage_delete', 6, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(10, '角色管理', 'page', 'role_manage', 1, 'el-icon-user', '/roleManage');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(11, '新增', 'button', 'role_manage_add', 10, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(12, '修改', 'button', 'role_manage_update', 10, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(13, '删除', 'button', 'role_manage_delete', 10, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(14, '权限管理', 'page', 'permission_manage', 1, 'el-icon-lock', '/accessManage');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(15, '新增', 'button', 'permission_manage_add', 14, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(16, '修改', 'button', 'permission_manage_update', 14, '', '');
INSERT INTO sys_permission (id, permission_name, resource_type, permission_value, parent_id, icon, url) VALUES(17, '删除', 'button', 'permission_manage_delete', 14, '', '');

# 添加超级管理员用户
insert into sys_user (id, user_name, `password`, tenant_id) values (0, 'admin', '$2a$10$ZBUTqDDGBstucLaA2IoVPe4ggYy3nFHOihhVdnT48eixT25LnZwca', 0);