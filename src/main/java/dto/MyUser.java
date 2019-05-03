package dto;

/**
 * 项目名称:   pinkstone
 * 包:        dto
 * 类名称:     MyUser
 * 类描述:     实体类
 * 创建人:     huangyang
 * 创建时间:   2019/5/1 14:57
 */
public class MyUser extends ParentUser {

    private int id;

    private String name;

    private SonUser son;

    public SonUser getSon () {
        return son;
    }

    public void setSon (SonUser son) {
        this.son = son;
    }

    public int getId ( ) {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    private void init() {

        System.out.println ("MyUser Init Start....");

    }

    private void des() {

        System.out.println ("MyUser Destroy Start....");

    }
}
