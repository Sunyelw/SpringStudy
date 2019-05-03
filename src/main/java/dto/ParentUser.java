package dto;

/**
 * 项目名称:   pinkstone
 * 包:        dto
 * 类名称:     ParentUser
 * 类描述:     parent dependent bean
 * 创建人:     huangyang
 * 创建时间:   2019/5/2 16:01
 */
public class ParentUser {

    private MyUser son;

    public MyUser getFather () {
        return son;
    }

    public void setFather (MyUser father) {
        this.son = father;
    }
}
