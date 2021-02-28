package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany // 실무에서 쓰지 않음, why? 필드를 더 추가하거나 단순히 맵핑하지 않을 경우에는 사용하기 어렵다
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    ) //객체는 다대다 관계가 가능하지만, 관계형db는 다대다 관계가 없기 때문에 일대다, 다대일로 푸는 중간 테이블이 있어야함
    private List<Item> items = new ArrayList<>();

    /*
    * Category같은 계층 구조는 어떻게 구현하나?
    * 같은 entity에서 연관관계 생성
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy =  "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 매서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
