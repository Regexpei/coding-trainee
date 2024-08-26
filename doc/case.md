```yaml
# 无配置
```

```yaml
# 全配置
thread:
  pool:
    config:
      - [5, 10, 200, 60]
      - [1, 1, 100, 60]
```

```yaml
# 部分配置
thread:
  pool:
    config:
      - [5, 10, 200, 60]
      -
      - [1, 1, 100, 60]
```