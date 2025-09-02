package com.zhora.admin.v1.system.user.business;

import com.zhora.admin.v1.system.user.dto.SysUserDetailDTO;
import com.zhora.admin.v1.system.user.dto.SysUserListDTO;
import com.zhora.admin.v1.system.user.vo.*;
import com.zhora.common.dto.page.PageDataGridRespDTO;

import java.util.List;

/**
 * 用户 业务层
 *
 * @author ruoyi
 */
public interface ISysUserBusinessService {

    PageDataGridRespDTO<SysUserListDTO> listPage(SysUserListVO listVO);

    /**
     * 根据条件分页查询用户列表
     *
     * @param listVO 用户信息
     * @return 用户信息集合信息
     */
    List<SysUserListDTO> selectUserList(SysUserListVO listVO);

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param listVO 用户信息
     * @return 用户信息集合信息
     */
    List<SysUserListDTO> selectAllocatedList(SysUserListVO listVO);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param listVO 用户信息
     * @return 用户信息集合信息
     */
    List<SysUserListDTO> selectUnallocatedList(SysUserListVO listVO);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserDetailDTO selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    SysUserDetailDTO selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    SysUserDetailDTO selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUserDetailDTO selectUserById(Long userId);

    /**
     * 通过用户ID查询用户和角色关联
     *
     * @param userId 用户ID
     * @return 用户和角色关联列表
     */
    List<SysUserListDTO> selectUserRoleByUserId(Long userId);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int deleteUserByIds(String ids);

    /**
     * 保存用户信息
     *
     * @param createVO 用户信息
     * @return 结果
     */
    int insertUser(SysUserCreateVO createVO);

    /**
     * 注册用户信息
     *
     * @param registerVO 用户信息
     * @return 结果
     */
    boolean registerUser(SysUserRegisterVO registerVO);

    /**
     * 保存用户信息
     *
     * @param updateVO 用户信息
     * @return 结果
     */
    int updateUser(SysUserUpdateVO updateVO);

    /**
     * 修改用户详细信息
     *
     * @param updateVO 用户信息
     * @return 结果
     */
    int updateUserInfo(SysUserUpdateVO updateVO);

    /**
     * 修改用户头像
     *
     * @param userId 用户ID
     * @param avatar 头像地址
     * @return 结果
     */
    boolean updateUserAvatar(Long userId, String avatar);

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * 修改用户密码信息
     *
     * @param updateVO 用户信息
     * @return 结果
     */
    int resetUserPwd(SysUserUpdateVO updateVO);

    /**
     * 校验用户名称是否唯一
     *
     * @param checkVO 用户信息
     * @return 结果
     */
    boolean checkLoginNameUnique(SysUserCheckVO checkVO);

    /**
     * 校验手机号码是否唯一
     *
     * @param checkVO 用户信息
     * @return 结果
     */
    boolean checkPhoneUnique(SysUserCheckVO checkVO);

    /**
     * 校验email是否唯一
     *
     * @param checkVO 用户信息
     * @return 结果
     */
    boolean checkEmailUnique(SysUserCheckVO checkVO);

    /**
     * 校验用户是否允许操作
     *
     * @param checkVO 用户信息
     */
    void checkUserAllowed(SysUserCheckVO checkVO);

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    void checkUserDataScope(Long userId);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    String selectUserRoleGroup(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userId 用户ID
     * @return 结果
     */
    String selectUserPostGroup(Long userId);

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    String importUser(List<SysUserListVO> userList, Boolean isUpdateSupport, String operName);

    /**
     * 用户状态修改
     *
     * @param updateVO 用户信息
     * @return 结果
     */
    int changeStatus(SysUserUpdateVO updateVO);
}
