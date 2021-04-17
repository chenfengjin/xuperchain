CXXFLAGS ?= -std=c++11 -Os -I/usr/local/include -Isrc -Werror=vla -I/home/chenfengjin/xuperchain/core/contractsdk/cpp/src
LDFLAGS ?= -Oz -s TOTAL_STACK=256KB -s TOTAL_MEMORY=1MB -s DETERMINISTIC=1 -s EXTRA_EXPORTED_RUNTIME_METHODS=["stackAlloc"] -L/usr/local/lib -lprotobuf-lite -lpthread --js-library /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/exports.js

.PHONY: all build clean

all: build

clean: 
	$(RM) -r build

/home/chenfengjin/.xdev-cache/d5/d585c2e5d1dbcea2.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/example/paillier/src/paillier.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/73/733eb503adfb06fa.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/example/paillier/src/paillier.pb.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/33/33b3cde028941397.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/account.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/0c/0c5d82fd4a409de9.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/basic_iterator.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/1a/1aff4627f02ddb81.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/block.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/5f/5f4836073cbead9c.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/context_impl.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/6c/6ccdeabe69f93808.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/contract.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/77/7783ddec5dd88377.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/contract.pb.c
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/89/89d035e2ef0043a9.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/crypto.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/5e/5ecad84eb33ea411.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/syscall.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/75/75be613369664c5e.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/transaction.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/b2/b2c1bf7a29d7d405.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/trust_operators/tf.pb.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/.xdev-cache/9e/9e05e3c12d9a42f7.o: /home/chenfengjin/xuperchain/core/contractsdk/cpp/src/xchain/trust_operators/trust_operators.cc
	@mkdir -p $(dir $@)
	@echo CC $(notdir $<)
	@$(CXX) $(CXXFLAGS) -MMD -MP -c $< -o $@

/home/chenfengjin/xuperchain/core/contractsdk/cpp/example/paillier/paillier.wasm: /home/chenfengjin/.xdev-cache/d5/d585c2e5d1dbcea2.o /home/chenfengjin/.xdev-cache/73/733eb503adfb06fa.o /home/chenfengjin/.xdev-cache/33/33b3cde028941397.o /home/chenfengjin/.xdev-cache/0c/0c5d82fd4a409de9.o /home/chenfengjin/.xdev-cache/1a/1aff4627f02ddb81.o /home/chenfengjin/.xdev-cache/5f/5f4836073cbead9c.o /home/chenfengjin/.xdev-cache/6c/6ccdeabe69f93808.o /home/chenfengjin/.xdev-cache/77/7783ddec5dd88377.o /home/chenfengjin/.xdev-cache/89/89d035e2ef0043a9.o /home/chenfengjin/.xdev-cache/5e/5ecad84eb33ea411.o /home/chenfengjin/.xdev-cache/75/75be613369664c5e.o /home/chenfengjin/.xdev-cache/b2/b2c1bf7a29d7d405.o /home/chenfengjin/.xdev-cache/9e/9e05e3c12d9a42f7.o
	@echo LD wasm
	@$(CXX) -o $@ $^ $(LDFLAGS)

build: /home/chenfengjin/xuperchain/core/contractsdk/cpp/example/paillier/paillier.wasm


-include /home/chenfengjin/.xdev-cache/d5/d585c2e5d1dbcea2.d /home/chenfengjin/.xdev-cache/73/733eb503adfb06fa.d /home/chenfengjin/.xdev-cache/33/33b3cde028941397.d /home/chenfengjin/.xdev-cache/0c/0c5d82fd4a409de9.d /home/chenfengjin/.xdev-cache/1a/1aff4627f02ddb81.d /home/chenfengjin/.xdev-cache/5f/5f4836073cbead9c.d /home/chenfengjin/.xdev-cache/6c/6ccdeabe69f93808.d /home/chenfengjin/.xdev-cache/77/7783ddec5dd88377.d /home/chenfengjin/.xdev-cache/89/89d035e2ef0043a9.d /home/chenfengjin/.xdev-cache/5e/5ecad84eb33ea411.d /home/chenfengjin/.xdev-cache/75/75be613369664c5e.d /home/chenfengjin/.xdev-cache/b2/b2c1bf7a29d7d405.d /home/chenfengjin/.xdev-cache/9e/9e05e3c12d9a42f7.d
