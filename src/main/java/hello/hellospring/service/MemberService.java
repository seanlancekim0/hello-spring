package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
// Spring은 해당 클래스의 method를 실행할 때 transaction을 시작하고,
// method가 정상 종료되면 transaction을 commit한다.
// if error 발생시 롤백한다.
// -> method가 정상종료 되지 않았을 시에 변경되던 데이터가 반영되지 않게하기 위함
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join(Member member) {
        //Ctrl + Alt + V: 객체 선언문 단축키
        //Ctre + Alt + Shift + T: Refactor 방법 출력 단축키
        //같은 이름의 중복회원은 안된다
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

//    전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
