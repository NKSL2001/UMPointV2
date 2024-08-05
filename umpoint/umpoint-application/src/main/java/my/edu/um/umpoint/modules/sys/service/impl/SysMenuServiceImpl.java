package my.edu.um.umpoint.modules.sys.service.impl;

import my.edu.um.umpoint.common.constant.Constant;
import my.edu.um.umpoint.common.exception.ErrorCode;
import my.edu.um.umpoint.common.exception.RenException;
import my.edu.um.umpoint.common.service.impl.BaseServiceImpl;
import my.edu.um.umpoint.common.utils.ConvertUtils;
import my.edu.um.umpoint.common.utils.TreeUtils;
import my.edu.um.umpoint.modules.security.user.UserDetail;
import my.edu.um.umpoint.modules.sys.dao.SysMenuDao;
import my.edu.um.umpoint.modules.sys.dto.SysMenuDTO;
import my.edu.um.umpoint.modules.sys.entity.SysMenuEntity;
import my.edu.um.umpoint.modules.sys.enums.SuperAdminEnum;
import my.edu.um.umpoint.modules.sys.service.SysMenuService;
import my.edu.um.umpoint.modules.sys.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public SysMenuDTO get(Long id) {
        SysMenuEntity entity = baseDao.getById(id);

        SysMenuDTO dto = ConvertUtils.sourceToTarget(entity, SysMenuDTO.class);

        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysMenuDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

        if (entity.getId().equals(entity.getPid())) {
            throw new RenException(ErrorCode.SUPERIOR_MENU_ERROR);
        }

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deleteById(id);

        sysRoleMenuService.deleteByMenuId(id);
    }

    @Override
    public List<SysMenuDTO> getAllMenuList(Integer menuType) {
        List<SysMenuEntity> menuList = baseDao.getMenuList(menuType);

        List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
    }

    @Override
    public List<SysMenuDTO> getUserMenuList(UserDetail user, Integer menuType) {
        List<SysMenuEntity> menuList;

        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            menuList = baseDao.getMenuList(menuType);
        } else {
            menuList = baseDao.getUserMenuList(user.getId(), menuType);
        }

        List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

        return TreeUtils.build(dtoList);
    }

    @Override
    public List<SysMenuDTO> getListPid(Long pid) {
        List<SysMenuEntity> menuList = baseDao.getListPid(pid);

        return ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
    }

}