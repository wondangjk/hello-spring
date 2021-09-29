package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemoryMemberRepository repository) {
        this.repository = repository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member){
        validteDuplicateMember(member);

        repository.save(member);
        return member.getId();
    }

    private void validteDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원X
        //Ctrl + alt + m extract method
        repository.findByName(member.getName()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId){

        return repository.findById(memberId);
    }
}
