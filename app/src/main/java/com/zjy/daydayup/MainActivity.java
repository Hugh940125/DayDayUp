package com.zjy.daydayup;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import com.zjy.daydayup.DesignPatterns.Bridge.Circle;
import com.zjy.daydayup.DesignPatterns.Bridge.GreenCircle;
import com.zjy.daydayup.DesignPatterns.Bridge.RedCircle;
import com.zjy.daydayup.DesignPatterns.Decorator.LeeSin;
import com.zjy.daydayup.DesignPatterns.Decorator.Skill_E;
import com.zjy.daydayup.DesignPatterns.Decorator.Skill_Q;
import com.zjy.daydayup.DesignPatterns.Decorator.Skill_W;
import com.zjy.daydayup.DesignPatterns.Factory.AnimalFactory;
import com.zjy.daydayup.DesignPatterns.Factory.IAnimal;
import com.zjy.daydayup.DesignPatterns.Filter.AndCriteria;
import com.zjy.daydayup.DesignPatterns.Filter.MaleCriteria;
import com.zjy.daydayup.DesignPatterns.Filter.OrCriteria;
import com.zjy.daydayup.DesignPatterns.Filter.SingleCriteria;
import com.zjy.daydayup.DesignPatterns.Filter.Student;
import com.zjy.daydayup.DesignPatterns.Observer.Receiver;
import com.zjy.daydayup.DesignPatterns.Observer.Sender;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_layout_test);
        ButterKnife.bind(this);

        DecoratorTest();
    }

    private void ObserverTest() {
        Receiver receiver = new Receiver();
        Sender sender = new Sender();
        sender.addObserver(receiver);
        sender.notifyObserver("添加了订阅，收到了");
        sender.removeObserver(receiver);
        sender.notifyObserver("添加了订阅，收到了");
    }

    private void FactoryTest() {
        AnimalFactory animalFactory = new AnimalFactory();
        IAnimal cat = animalFactory.getAnimal("cat");
        cat.eat();
        IAnimal dog = animalFactory.getAnimal("dog");
        dog.eat();
    }

    private void BridgeTest() {
        Circle RedCircle = new Circle(0, 0, 0, new RedCircle());
        Circle GreenCircle = new Circle(0, 0, 0, new GreenCircle());

        RedCircle.draw();
        GreenCircle.draw();
    }

    private void FilterTest() {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student("ZhaSan", "male", "single");
        students.add(student1);
        Student student2 = new Student("ZhaSi", "male", "single");
        students.add(student2);
        Student student3 = new Student("ZhaWu", "male", "not_single");
        students.add(student3);
        Student student4 = new Student("ZhaLiu", "female", "not_single");
        students.add(student4);
        Student student5 = new Student("ZhaQi", "female", "single");
        students.add(student5);
        Student student6 = new Student("ZhaBa", "male", "single");
        students.add(student6);

        MaleCriteria maleCriteria = new MaleCriteria();
        List<Student> students1 = maleCriteria.meetCriteria(students);
        Log.e("男的", students1.toString());
        SingleCriteria singleCriteria = new SingleCriteria();
        List<Student> students2 = singleCriteria.meetCriteria(students);
        Log.e("单身狗", students2.toString());
        OrCriteria orCriteria = new OrCriteria(maleCriteria, singleCriteria);
        List<Student> students3 = orCriteria.meetCriteria(students);
        Log.e("男的或者单身狗", students3.toString());
        AndCriteria andCriteria = new AndCriteria(maleCriteria, singleCriteria);
        List<Student> students4 = andCriteria.meetCriteria(students);
        Log.e("男的单身狗", students4.toString());
    }

    private void DecoratorTest() {
        LeeSin leeSin = new LeeSin();
        Skill_Q skill_q = new Skill_Q(leeSin, "天音波、回音击");
        Skill_W skill_w = new Skill_W(skill_q, "金钟罩、铁布衫");
        Skill_E skill_e = new Skill_E(skill_w, "天雷破、摧经断骨");
        skill_e.learnSkill();
    }
}
